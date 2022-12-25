package com.example.mysqllearningdemo.pojo.vo;

public enum SearchOperator {
    EQUALS("="),
    NOT_EQUALS("!="),
    GREATER(">"),
    LESS("<"),
    BETWEEN("BETWEEN"),
    IN("IN"),
    NOT_IN("NOT IN"),
    LIKE("LIKE"),
    NOT_LIKE("NOT LIKE"),
    REGEXP("REGEXP"),
    IS("IS"),
    IS_NOT("IS NOT")
    ;

    private String operator;

    SearchOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return   operator;
    }
}
