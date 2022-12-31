package com.example.mysqllearningdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mysqllearningdemo.common.Result;
import com.example.mysqllearningdemo.mapper.QuestionMapper;
import com.example.mysqllearningdemo.pojo.Question;
import com.example.mysqllearningdemo.pojo.vo.SearchVO;
import com.example.mysqllearningdemo.service.impl.QuestionServiceImpl;
import com.example.mysqllearningdemo.utils.MPUtils;
import com.example.mysqllearningdemo.pojo.dto.ListDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.Resource;
import java.util.List;

/**
 *@描述
 *@创建人  yxy
 *@创建时间  2022/12/19
 */
@Api(value = "题库",tags = {"题库"})
@Slf4j
@RequestMapping("/question")
@RestController
public class QuestionController {

    @Autowired
    QuestionServiceImpl questionService;

    @Resource
    QuestionMapper questionMapper;


    @ApiOperation("每日一题")
    @GetMapping(value = "/daily/question")
    public Result dailyQuestion(@RequestParam("userId") String userId){
        // TODO RabbitMQ
        // 设置超时时间

        return null;
    }

    @ApiOperation("分页查询")
    @PostMapping (value = "/list")
    public Result list(@RequestBody ListDTO listDTO){
        Page<Question> page = new Page<>(listDTO.getPageIndex(), listDTO.getPageSize(), listDTO.isCounted());
        QueryWrapper wrapper = MPUtils.toQueryWrapper(listDTO.getWheres());
        Page pageResult = questionMapper.selectPage(page, wrapper);
        return new Result(new SearchVO(pageResult.getPages(), pageResult.getCurrent(),
                pageResult.getRecords(), pageResult.getSize(), pageResult.getTotal()));
    }

    @ApiOperation("增加")
    @PostMapping(value = "/insert")
    public Result insert(@RequestBody Question question){
        return new Result(questionService.save(question));
    }

    @ApiOperation("删除")
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam String id){
        return new Result(questionService.removeById(id));
    }

    @ApiOperation("更新")
    @PostMapping(value = "/update")
    public Result update(@RequestBody Question question){
        return new Result(questionService.updateById(question));
    }

    @ApiOperation("批量删除")
    @PostMapping(value = "/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return new Result(questionService.removeByIds(ids));
    }

}
