package com.qinxc.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qxc
 * @date 2019/1/9.
 */
public class TwoPrint {

    public static volatile int clock;

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(2);
        Lock lock = new ReentrantLock();

        Thread printer1 = new Thread(new Printer(0, countDownLatch, lock));
        Thread printer2 = new Thread(new Printer(1, countDownLatch, lock));

        printer1.start();
        printer2.start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class Printer implements Runnable {

    int selfNumber;
    int printerNumber;
    CountDownLatch countDownLatch;
    Lock lock;

    public Printer(int selfNumber, CountDownLatch countDownLatch, Lock lock) {
        this.selfNumber = selfNumber;
        this.countDownLatch = countDownLatch;
        this.lock = lock;
        this.printerNumber = selfNumber;
    }

    @Override
    public void run() {

        while (true) {

            try {
                lock.lock();
                if (TwoPrint.clock > 100) {
                    countDownLatch.countDown();
                    break;
                }
                if (TwoPrint.clock % 2 == selfNumber && TwoPrint.clock <= 100) {
                    System.out.println("Printer" + selfNumber + ":" + TwoPrint.clock);
                    TwoPrint.clock++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}