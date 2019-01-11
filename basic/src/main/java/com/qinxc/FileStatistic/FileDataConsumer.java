package com.qinxc.FileStatistic;

import java.util.TreeSet;

/**
 * @author qxc
 * @date 2019/1/9.
 */
public class FileDataConsumer implements Runnable {

    DataCenter dataCenter;
    int number;

    @Override
    public void run() {
        Message message = dataCenter.topic.get(number % 10).pollFirst();
        TreeSet<Double> groupSort = dataCenter.result.getOrDefault(message.groupId, new TreeSet<>());
        groupSort.add(message.value);
        dataCenter.result.put(message.groupId, groupSort);

    }
}
