package com.example.mysqllearningdemo.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("分页查询结果")
@AllArgsConstructor
public class SearchVO {

    @ApiModelProperty("当前页数")
    private long pages;

    @ApiModelProperty("当前页数据条数")
    private long current;

    @ApiModelProperty("查询结果")
    private List records;

    @ApiModelProperty("每页数据条数")
    private long size;

    @ApiModelProperty("总数据条数")
    private long total;

}
