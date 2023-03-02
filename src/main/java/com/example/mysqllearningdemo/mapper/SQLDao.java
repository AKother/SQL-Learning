package com.example.mysqllearningdemo.mapper;

import com.example.mysqllearningdemo.common.Result;
import com.example.mysqllearningdemo.common.ResultCode;
import com.example.mysqllearningdemo.config.DatabaseConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.*;
import java.util.*;

import static com.example.mysqllearningdemo.config.DatabaseConfig.DATASOURCE_PASSWORD;
import static com.example.mysqllearningdemo.config.DatabaseConfig.DATASOURCE_USERNAME;

@Slf4j
@Repository
public class SQLDao {

    @Resource
    UserMapper userMapper;

    Connection conn = null;
    Statement stat = null;
    PreparedStatement preparedStatement = null;

    public boolean createDatabase(String email) throws Exception {
        try {
            Class.forName(DatabaseConfig.DATABASE_DRIVER);
            //一开始必须填一个已经存在的数据库
            conn = DriverManager.getConnection(DatabaseConfig.DATASOURCE_URL,
                    DatabaseConfig.DATASOURCE_USERNAME,
                    DatabaseConfig.DATASOURCE_PASSWORD);
            conn.setAutoCommit(false);
            stat = conn.createStatement();
            //为该用户新建一个数据库 库名字为 ml+邮箱
            stat.executeUpdate("create database " +"ml" + email.replace("@","").replace(".",""));
            conn.commit();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            conn.rollback();
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            stat.close();
            conn.close();
            return true;
        }
    }

    public Result selectExecute(String userId, String sql) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        // 连接上自己的数据库
        try {
            String email = userMapper.getEmailById(userId).replace("@","").replace(".","");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ml" + email + "?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false",
                    DatabaseConfig.DATASOURCE_USERNAME,
                    DatabaseConfig.DATASOURCE_PASSWORD);
        }catch (Exception e) {
            log.error("SQLDao, selectExecute:{}",e);
            resultList = new ArrayList<>();
            HashMap<String, Object> map = new HashMap<>(1);
            map.put("error", e.getMessage());
            resultList.add(map);
        }
        try{
            // 执行SQL
            conn.setAutoCommit(false);
            stat = conn.createStatement();
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();

            // 表名
            HashMap<String, Object> tableNameMap = new HashMap<>();
            tableNameMap.put("tableName", metaData.getTableName(1));
            resultList.add(tableNameMap);

            // 列名
            List<String> columnList = new ArrayList<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                columnList.add(metaData.getColumnName(i));
            }
            HashMap<String, Object> columnMap = new HashMap<>();
            columnMap.put("columnName", columnList);
            resultList.add(columnMap);

            // 数据集
            while (resultSet.next()) {
                List<String> value = new LinkedList<>();
                for (int i = 1; i <= columnList.size(); i++) {
                    value.add(resultSet.getString(i));
                }
                HashMap<String, Object> valueMap = new HashMap<>();
                valueMap.put("data", value);
                resultList.add(valueMap);
            }
            conn.commit();
            preparedStatement.close();
            stat.close();
            conn.close();
        } catch (Exception e) {
            log.error("SQLDao, selectExecute:{}",e);
            return new Result(ResultCode.Failure.getCode(), e.getMessage());
        }
        return new Result(resultList);
    }

    public Result unSelectExecute(String userId, String sql) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        // 连接上自己的数据库
        try {
            String email = userMapper.getEmailById(userId).replace("@","").replace(".","");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ml" + email + "?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false",
                    "root",
                    "Akother721202");
        }catch (Exception e) {
            log.error("SQLDao, selectExecute:{}",e);
            return new Result(ResultCode.Failure.getCode(), e.getMessage());
        }
        Integer result = 0;
        try {
            String email = userMapper.getEmailById(userId).replace("@","").replace(".","");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ml" + email +"?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false",
                    "root",
                    "Akother721202");
            conn.setAutoCommit(false);
            stat = conn.createStatement();
            preparedStatement = conn.prepareStatement(sql);
            result = preparedStatement.executeUpdate();
            conn.commit();
            preparedStatement.close();
            stat.close();
            conn.close();
        } catch (Exception e) {
            log.error("SQLDao, unSelectExecute:{}",e);
            return new Result(ResultCode.Failure.getCode(), e.getMessage());
        }
        return new Result(result);
    }

    public Result getTablesByUser(String userId){
        List<Map<String, Object>> resultList = new ArrayList<>();
        // 连接上自己的数据库
        try {
            String email = userMapper.getEmailById(userId).replace("@","").replace(".","");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ml" + email + "?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false",
                    "root",
                    "Akother721202");
        }catch (Exception e) {
            log.error("SQLDao, getTablesByUser:{}",e);
            resultList = new ArrayList<>();
            HashMap<String, Object> map = new HashMap<>(1);
            map.put("error", e.getMessage());
            resultList.add(map);
            return new Result(resultList);
        }
        try{
            // 执行SQL
            conn.setAutoCommit(false);
            stat = conn.createStatement();
            preparedStatement = conn.prepareStatement("show tables");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> tables = new ArrayList<>();
            while(resultSet.next()){
                tables.add(resultSet.getString(1));
            }
            conn.commit();
            preparedStatement.close();
            stat.close();
            conn.close();
            return new Result(tables);
        } catch (Exception e) {
            log.error("SQLDao, getTableByUser:{}",e);
            return new Result(ResultCode.Failure.getCode(), e.getMessage());
        }
    }

}
