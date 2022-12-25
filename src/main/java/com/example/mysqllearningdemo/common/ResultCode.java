package com.example.mysqllearningdemo.common;

public enum ResultCode {

    Failure(-1, "操作失败！"),
    Success(0, "操作成功！");

    private Integer code;

    private String message = "操作失败！";

    ResultCode(Integer code) {
        this.code = code;
    }

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
