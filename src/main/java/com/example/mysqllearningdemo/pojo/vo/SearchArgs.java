package com.example.mysqllearningdemo.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApiModel(value="查询条件对象",description="前端尽量按照标准组织此对象")
public class SearchArgs {


    @ApiModelProperty(value="字段名（后端保持字段名和数据库字段一致)", name="key",example="")
    private String key;

    @ApiModelProperty(value="> < = like ", name="operator",example="")
    private String operator;

    @ApiModelProperty(value="字段值", name="value",example="")
    private Object value;


    public SearchArgs() {
    }

    public SearchArgs(String key, String operator, Object value) {
        this.key = key;
        this.operator = operator;
        this.value = value;
    }

    /**
     * 将List<SearchArgs>转化为Map<String, Object>
     * @param searchArgsList   查询参数list
     * @return                      查询参数map
     */
    public static Map<String, Object> parseToMap(List<SearchArgs> searchArgsList) {
        Map<String, Object> map = new HashMap<>();
        for (SearchArgs searchArgsItem : searchArgsList) {
            map.put(searchArgsItem.getKey() + searchArgsItem.getOperator(), searchArgsItem.getValue());
        }
        return map;
    }

    public static List<SearchArgs> parseToList(Map<String, Object> map, SearchOperator searchOperator) {
        List<SearchArgs> searchArgsList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                if (entry.getValue() instanceof String && ((String) entry.getValue()).trim().isEmpty()) {
                    continue;
                }
                if (entry.getValue() instanceof Number && entry.getValue().equals(-1)) {
                    continue;
                }
                SearchArgs searchArgs = new SearchArgs();
                searchArgs.setKey(entry.getKey());
                searchArgs.setValue(entry.getValue());
                searchArgs.setOperator(searchOperator.getOperator());

                searchArgsList.add(searchArgs);
            }
        }
        return searchArgsList;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SearchArgs{" +
                "key='" + key + '\'' +
                ", operator='" + operator + '\'' +
                ", value=" + value +
                '}';
    }
}