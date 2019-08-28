package com.jiangguang.entity;

import java.util.LinkedList;
import java.util.List;

public class ResultMsg<T> {

    private Integer code;

    private Long count;

    private String msg;

    private List<T> data = new LinkedList<>();

    public Integer getCode() {
        return code;
    }

    public Long getCount() {
        return count;
    }

    public String getMsg() {
        return msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
