package com.example.mysqllearningdemo.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户信息", description = "用户信息")
public class UserDTO {

    @ApiModelProperty(value = "userId")
    private String userId;

    @ApiModelProperty(value = "tableName")
    private String tableName;
}
