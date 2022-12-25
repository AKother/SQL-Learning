package com.example.mysqllearningdemo.common;

public enum SQLType {

    SELECT(1),
    UN_SELECT(2);

    private Integer code;

    SQLType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }

}
