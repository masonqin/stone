package com.qinxc.minirpc.service;

/**
 * @author qxc
 * @date 2018/11/30.
 */
public class Result {

    String field;

    public Result(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "Result{" +
                "field='" + field + '\'' +
                '}';
    }
}
