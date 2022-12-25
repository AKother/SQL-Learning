package com.example.mysqllearningdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mysqllearningdemo.common.RedisConstants;
import com.example.mysqllearningdemo.common.Result;
import com.example.mysqllearningdemo.common.ResultCode;
import com.example.mysqllearningdemo.mapper.UserMapper;
import com.example.mysqllearningdemo.pojo.User;
import com.example.mysqllearningdemo.pojo.dto.ListDTO;
import com.example.mysqllearningdemo.pojo.vo.LoginVO;
import com.example.mysqllearningdemo.pojo.vo.SearchVO;
import com.example.mysqllearningdemo.service.impl.SQLServiceImpl;
import com.example.mysqllearningdemo.service.impl.UserServiceImpl;
import com.example.mysqllearningdemo.utils.EmailSender;
import com.example.mysqllearningdemo.utils.MD5Utils;
import com.example.mysqllearningdemo.utils.MPUtils;
import com.example.mysqllearningdemo.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "用户",tags = {"用户"})
@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Resource
    UserMapper userMapper;

    @Autowired
    SQLServiceImpl sqlService;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    EmailSender emailSender;

    @ApiOperation(value = "发送邮箱验证码", notes = "过期时间5分钟")
    @GetMapping(value = "/email/code")
    public Result getEmailCode(@RequestParam("email") String email){
        // 判断是否被注册过
        if (userMapper.getUserByEmail(email) != null){
            return new Result(ResultCode.Failure.getCode(), "邮箱已被注册");
        }
        String code = emailSender.getEmailCode();
        emailSender.sendRegisterEmail(email, code);
        redisUtils.set(RedisConstants.USER_EMAIL_CODE + email, code, 300);
        return new Result(code);
    }

    @ApiOperation(value = "注册", notes = "先调用获取邮箱验证码接口，再调用该接口")
    @PostMapping(value = "/register")
    public Result register(@RequestBody User user,
                           @RequestParam("code") String code){
        // 校验验证码
        if( !redisUtils.get(RedisConstants.USER_EMAIL_CODE + user.getEmail()).equals(code)){
            return new Result(ResultCode.Failure.getCode(), "验证码错误或已过期");
        }
        // 为用户创建数据库
        sqlService.createDatabase(user.getEmail());
        user.setPassword(MD5Utils.MD5EncryptPassword(user.getName(), user.getPassword()));
        return new Result(userMapper.insert(user));
    }

    @ApiOperation(value = "登录", notes = "只要传账号和密码")
    @PostMapping(value = "/login")
    public Result login(@RequestBody LoginVO loginVO){
        User user = userMapper.getUserByNameAndPassword(loginVO.getName(),
                MD5Utils.MD5EncryptPassword(loginVO.getName(), loginVO.getPassword()));
        if(user != null) {
            return new Result(ResultCode.Success.getCode(), "登录成功");
        }
        return new Result(ResultCode.Failure.getCode(), "登录失败，账号或密码错误");
    }

    @ApiOperation("分页查询")
    @PostMapping(value = "/list")
    public Result list(@RequestBody ListDTO listDTO){
        Page<User> page = new Page<>(listDTO.getPageIndex(), listDTO.getPageSize(), listDTO.isCounted());
        QueryWrapper wrapper = MPUtils.toQueryWrapper(listDTO.getWheres());
        Page pageResult = userMapper.selectPage(page, wrapper);
        return new Result(new SearchVO(pageResult.getPages(), pageResult.getCurrent(),
                pageResult.getRecords(), pageResult.getSize(), pageResult.getTotal()));
    }

    @ApiOperation("插入")
    @PostMapping(value = "/insert")
    public Result insert(@RequestBody User user){
        return new Result(userService.save(user));
    }

    @ApiOperation("删除")
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam String id){
        return new Result(userService.removeById(id));
    }

    @ApiOperation("更新")
    @PostMapping(value = "/update")
    public Result update(@RequestBody User user){
        return new Result(userService.updateById(user));
    }

    @ApiOperation("批量删除")
    @PostMapping(value = "/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return new Result(userService.removeByIds(ids));
    }

}
