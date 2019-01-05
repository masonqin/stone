package com.qinxc.basic;

/**
 * @author qxc
 * @date 2018/11/28.
 */
public class MyHandler {

    public MyHandler(String name, String number) {
        this.name = name;
        this.number = number;
    }

    String name;
    String number;

    @Override
    public String toString() {
        return "MyHandler{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
