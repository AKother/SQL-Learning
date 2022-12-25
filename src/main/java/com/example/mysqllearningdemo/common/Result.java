package com.example.mysqllearningdemo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.log4j.spi.ErrorCode;

import java.io.Serializable;

@ApiModel(value = "返回的对象", description = "code = 0：表示数据正常，其它状态可自定义")
public class Result implements Serializable {

    @ApiModelProperty(value = "响应码 (0:正常) ", name = "code")
    private Integer code = -1;

    @ApiModelProperty(value = "返回消息", name = "message")
    private String message;

    @ApiModelProperty(value = "返回数据", name = "data")
    private Object data;

    @ApiModelProperty(value = "是否成功", name = "success")
    private Boolean success = Boolean.FALSE;

    public Result() {
    }

    public Result(boolean isSuccess){
        if(isSuccess){
            this.code = ResultCode.Success.getCode();
            this.message = ResultCode.Success.getMessage();
        }else{
            this.code = ResultCode.Failure.getCode();
            this.message = "error";
        }
        setSuccess(isSuccess);
    }

    public Result(Object data) {
        this.code = ResultCode.Success.getCode();
        this.message = ResultCode.Success.getMessage();
        this.data = data;
        setSuccess(isSuccess());
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
        setSuccess(isSuccess());
    }

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
        setSuccess(isSuccess());
    }

    public Result(Exception e) {
        this.code = ResultCode.Failure.getCode();
        this.message = e.getMessage();
        setSuccess(isSuccess());
    }

    public Boolean isSuccess() {
        return code.equals(ResultCode.Success.getCode());
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public Result setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return String.format("Result: {code = %s, message = '%s', data = %s}", code, message, data);
    }

}
