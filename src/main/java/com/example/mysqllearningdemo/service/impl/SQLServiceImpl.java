package com.example.mysqllearningdemo.service.impl;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.example.mysqllearningdemo.common.Result;
import com.example.mysqllearningdemo.common.ResultCode;
import com.example.mysqllearningdemo.common.SQLType;
import com.example.mysqllearningdemo.mapper.SQLDao;
import com.example.mysqllearningdemo.service.SQlService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Service
@Slf4j
public class SQLServiceImpl implements SQlService {

    @Resource
    SQLDao sqlDao;


    @Override
    public Result createDatabase(String email) {
        try{
            sqlDao.createDatabase(email);
        }catch (Exception e){
            return new Result(ResultCode.Failure.getCode(), e.getMessage());
        }
        return new Result(ResultCode.Success.getCode());
    }

    @Override
    public Result isSQLIllegal(String sql) {
        List<SQLStatement> statementList = null;
        SQLStatementParser parser = null;
        try {
            parser = SQLParserUtils.createSQLStatementParser(sql, "mysql");
            parser.getExprParser().getDbType();
            statementList = parser.parseStatementList();
            System.out.println(statementList);
        } catch (Exception e) {
            return new Result(ResultCode.Failure.getCode(), e.getMessage());
        }
        return new Result(ResultCode.Success.getCode());
    }

    @Override
    public Result executeSQL(String userId, String sql){
        if (!isSQLIllegal(sql).isSuccess()) {
            return isSQLIllegal(sql);
        }
        if(getSQLType(sql).equals(SQLType.SELECT)){
            log.info("SELECT SQL: {}", sql);
            return sqlDao.selectExecute(userId, sql);
        }
        log.info("UNSELECT SQL: {}", sql);
        return sqlDao.unSelectExecute(userId, sql);
    }



    @Override
    public SQLType getSQLType(String sql) {
        String[] sqlArray = sql.split(" ");
        if(sqlArray[0].equals("select") || sqlArray[0].equals("SELECT")){
            return SQLType.SELECT;
        }
        return SQLType.UN_SELECT;
    }

    @Override
    public Result getTablesByUser(String userId) {
        return sqlDao.getTablesByUser(userId);
    }

    @Override
    public Result getTableDetails(String userId, String tableName){
        return this.executeSQL(userId, "select * from " + tableName);
    }

}
