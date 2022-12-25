package com.example.mysqllearningdemo.service;

import com.example.mysqllearningdemo.common.Result;
import com.example.mysqllearningdemo.common.SQLType;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface SQlService {

    // 创建数据库
    Result createDatabase(String email);

    // 判断SQL是否有错
    Result isSQLIllegal(String sql);

    // 执行SQL并拿到返回结果
    Result executeSQL(String userId, String sql) throws SQLException;

    // 获取SQL类型
    SQLType getSQLType(String sql);

    // 获取当前用户创建的表
    Result getTablesByUser(String userId);

    // 获取当前用户创建的表的全部内容
    Result getTableDetails(String userId, String tableName);

}
