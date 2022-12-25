package com.example.mysqllearningdemo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mysqllearningdemo.common.Result;
import com.example.mysqllearningdemo.mapper.HistoryMapper;
import com.example.mysqllearningdemo.pojo.History;
import com.example.mysqllearningdemo.pojo.dto.ListDTO;
import com.example.mysqllearningdemo.pojo.vo.SearchVO;
import com.example.mysqllearningdemo.service.impl.HistoryServiceImpl;
import com.example.mysqllearningdemo.utils.MPUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Api(value = "用户编译历史记录",tags = {"用户编译历史记录"})
@Slf4j
@RequestMapping("/history")
@RestController
public class HistoryController {

    @Autowired
    HistoryServiceImpl historyService;

    @Resource
    HistoryMapper historyMapper;

    @ApiOperation("分页查询")
    @PostMapping(value = "/list")
    public Result list(@RequestBody ListDTO listDTO){
        Page<History> page = new Page<>(listDTO.getPageIndex(), listDTO.getPageSize(), listDTO.isCounted());
        QueryWrapper wrapper = MPUtils.toQueryWrapper(listDTO.getWheres());
        Page pageResult = historyMapper.selectPage(page, wrapper);
        return new Result(new SearchVO(pageResult.getPages(), pageResult.getCurrent(),
                pageResult.getRecords(), pageResult.getSize(), pageResult.getTotal()));
    }

    @ApiOperation("插入")
    @PostMapping(value = "/insert")
    public Result insert(@RequestBody History history){
        return new Result(historyService.save(history));
    }

    @ApiOperation("删除")
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam String id){
        return new Result(historyService.removeById(id));
    }

    @ApiOperation("更新")
    @PostMapping(value = "/update")
    public Result update(@RequestBody History history){
        return new Result(historyService.updateById(history));
    }

    @ApiOperation("批量删除")
    @PostMapping(value = "/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return new Result(historyService.removeByIds(ids));
    }

}
