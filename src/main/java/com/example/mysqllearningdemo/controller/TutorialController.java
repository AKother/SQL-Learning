package com.example.mysqllearningdemo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mysqllearningdemo.common.Result;
import com.example.mysqllearningdemo.mapper.TutorialMapper;
import com.example.mysqllearningdemo.pojo.Tutorial;
import com.example.mysqllearningdemo.pojo.dto.ListDTO;
import com.example.mysqllearningdemo.pojo.vo.SearchVO;
import com.example.mysqllearningdemo.service.impl.TutorialServiceImpl;
import com.example.mysqllearningdemo.utils.MPUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "教程",tags = {"教程"})
@Slf4j
@RequestMapping("/tutorial")
@RestController
public class TutorialController {

    @Autowired
    TutorialServiceImpl tutorialService;

    @Resource
    TutorialMapper tutorialMapper;

    @ApiOperation("分页查询")
    @PostMapping (value = "/list")
    public Result list(@RequestBody ListDTO listDTO){
        Page<Tutorial> page = new Page<>(listDTO.getPageIndex(), listDTO.getPageSize(), listDTO.isCounted());
        QueryWrapper wrapper = MPUtils.toQueryWrapper(listDTO.getWheres());
        Page pageResult = tutorialMapper.selectPage(page, wrapper);
        return new Result(new SearchVO(pageResult.getPages(), pageResult.getCurrent(),
                pageResult.getRecords(), pageResult.getSize(), pageResult.getTotal()));
    }

    @ApiOperation("插入")
    @PostMapping(value = "/insert")
    public Result insert(@RequestBody Tutorial tutorial){
        return new Result(tutorialService.save(tutorial));
    }

    @ApiOperation("删除")
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam String id){
        return new Result(tutorialService.removeById(id));
    }

    @ApiOperation("更新")
    @PostMapping(value = "/update")
    public Result update(@RequestBody Tutorial tutorial){
        return new Result(tutorialService.updateById(tutorial));
    }

    @ApiOperation("批量删除")
    @PostMapping(value = "/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return new Result(tutorialService.removeByIds(ids));
    }

}
