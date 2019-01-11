package com.qinxc.FileStatistic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.concurrent.CountDownLatch;

/**
 * @author qxc
 * @date 2019/1/9.
 */
public class FileDataProducer implements Runnable {

    DataCenter dataCenter;
    String filePath;
    CountDownLatch countDownLatch;

    public FileDataProducer(DataCenter dataCenter, String filePath, CountDownLatch countDownLatch) {
        this.dataCenter = dataCenter;
        this.filePath = filePath;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        FileInputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = new FileInputStream(filePath);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                //可以抽象一个DataParser
                String[] contentArr = line.split(",");
                Message message = new Message();
                message.id = Long.parseLong(contentArr[0]);
                message.groupId = Long.parseLong(contentArr[1]);
                message.value = Double.parseDouble(contentArr[3]);
                dataCenter.push(message);
            }
            countDownLatch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                reader = null;
                inputStream = null;
            }
        }

    }

}
