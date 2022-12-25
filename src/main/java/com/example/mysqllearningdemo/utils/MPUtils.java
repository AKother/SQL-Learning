package com.example.mysqllearningdemo.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mysqllearningdemo.pojo.vo.SearchArgs;
import org.springframework.stereotype.Component;

@Component
public class MPUtils {

    public static QueryWrapper toQueryWrapper(SearchArgs... wheres) {
        QueryWrapper queryWrapper = new QueryWrapper();
        String key="";
        String op="";
        Object value=null;
        if(null!=wheres && wheres.length>0) {
            for (SearchArgs arg : wheres) {
                key = arg.getKey();
                op=arg.getOperator();
                value=arg.getValue();
                switch (op){
                    case "=": queryWrapper.eq(key,value);break;
                    case "!=": queryWrapper.ne(key,value);break;
                    case "<>": queryWrapper.ne(key,value);break;
                    case "like": queryWrapper.like(key,value);break;
                    case "leftlike": queryWrapper.likeLeft(key,value);break;
                    case "rightlike": queryWrapper.likeRight(key,value);break;
                    case "notlike": queryWrapper.notLike(key,value);break;
                    case ">": queryWrapper.gt(key,value);break;
                    case "<": queryWrapper.lt(key,value);break;
                    case ">=": queryWrapper.ge(key,value);break;
                    case "<=": queryWrapper.le(key,value);break;
                    case "or": queryWrapper.or();break;
                    case "inSql": queryWrapper.inSql(key,value.toString());break;
                    case "in": queryWrapper.inSql(key,value.toString());break;
                }
            }
        }
        return queryWrapper;
    }

}
