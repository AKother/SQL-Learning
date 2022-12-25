package com.example.mysqllearningdemo.pojo.dto;

import com.example.mysqllearningdemo.pojo.vo.SearchArgs;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询条件", description = "如果不设置，就不需要传值")
public class ListDTO {

    @ApiModelProperty(value = "1", name = "pageIndex")
    int pageIndex = 1;

    @ApiModelProperty(value = "15", name = "pageSize")
    int pageSize = 1;

    @ApiModelProperty(value = "是否返回总数", name = "counted")
    boolean counted;

    @ApiModelProperty(value="条件数组",name="wheres",example="")
    SearchArgs[] wheres;
}
