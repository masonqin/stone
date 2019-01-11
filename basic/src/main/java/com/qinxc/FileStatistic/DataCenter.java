package com.qinxc.FileStatistic;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author qxc
 * @date 2019/1/9.
 */
public class DataCenter {

    //消息路由在这里实现 用ConcurrentHashMap实现存储
    ConcurrentHashMap<Integer, BlockingDeque<Message>> topic = new ConcurrentHashMap<>();
    Map<Long, TreeSet<Double>> result = new HashMap<>();

    public void push(Message message) {
        BlockingDeque<Message> partition = topic.getOrDefault(message.groupId % 10, new LinkedBlockingDeque<>());
        partition.offer(message);
        topic.put((int) (message.groupId % 10), partition);
    }

}
