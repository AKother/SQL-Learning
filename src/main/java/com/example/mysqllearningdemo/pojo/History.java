package com.example.mysqllearningdemo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("用户SQL历史记录")
public class History {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer id;


    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @TableField("`sql`")
    @ApiModelProperty(value = "sql语句")
    private String sql;

    @TableField("`result`")
    @ApiModelProperty(value = "sql运行结果")
    private String result;

    @ApiModelProperty(value = "sql创建时间")
    private Date createTime;

    @ApiModelProperty(value = "是否被收藏 0-否 1-是（默认为0）")
    private Integer isStar;

}
