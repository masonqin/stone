package com.qinxc.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Thread2_MultiTurn2 {

    Object threadLock = new Object();
    //volatile int globalId = 0;
    AtomicInteger globalId = new AtomicInteger();
    int stopCnt = 10;

    public static void main(String[] args) throws Exception {

        Thread2_MultiTurn2 parent = new Thread2_MultiTurn2();
        List<Thread> threads= new ArrayList<>();
        parent.globalId.getAndSet(0);

        long startTime = System.currentTimeMillis();
        for(int i=0 ;i<10;i++){
            int a = 'A' + i;
            String name = String.valueOf((char)a);
            threads.add(new Thread(parent.new childThread(name, parent.stopCnt, i)));
        }


        for(Thread thread : threads){
            thread.start();
        }

        for(Thread thread : threads) {
            thread.join();
        }

        long stopTime = System.currentTimeMillis();

        System.out.println("Time : " + (stopTime-startTime));

    }
    class childThread implements Runnable {

        String name;
        int selfCnt = 0;
        int stopCnt;
        int selfId;

        public childThread(String name, int stopCnt, int selfId) {
            this.name = name;
            this.stopCnt = stopCnt;
            this.selfId = selfId;
        }

        @Override
        public void run() {
            process();
            System.out.println("=====Thread end : " + this.name);
        }

        public void process() {
            synchronized (threadLock) {
                while (true) {
                    try {
                        while (selfId != globalId.get() % 10) {
                            threadLock.wait();
                        }
                        System.out.println("Name:" + this.name + "\tselfId:" + this.selfId + "\tselfCnt:" + selfCnt);
                        selfCnt++;
                        //globalId++;
                        globalId.getAndIncrement();
                        threadLock.notifyAll();
                        if (selfCnt == stopCnt)
                            break;
                    } catch (Exception e) {

                    }
                }
            }

        }

    }


}


