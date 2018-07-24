package com.qinxc.basic;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class MyLogger {

    // 首先获得日志记录这个对象
    static private Logger logger = LoggerFactory.getLogger(MyLogger.class);

    public static void main(String[] args) {
        // 记录error信息
        logger.error("[info message]");
        // 记录info，还可以传入参数
        logger.info("[info message]{},{},{},{}", "abc", false, 123, new MyLogger());
        // 记录deubg信息
        logger.debug("[debug message]");
        // 记录trace信息
        logger.trace("[trace message]");
        System.out.println("hello world");

        Object mapobj = new Object();
        mapobj.hashCode();

//        ClassLoader classLoader =
//                Class<>


    }

}
