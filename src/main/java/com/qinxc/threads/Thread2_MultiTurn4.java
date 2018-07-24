package com.qinxc.threads;

public class Thread2_MultiTurn4 {

    volatile int a[] = new int[100];
    volatile int b[] = new int[100];
    volatile int c[] = new int[100];
    volatile int d[] = new int[100];

    volatile int indexa = 0;
    volatile int indexb = 0;
    volatile int indexc = 0;
    volatile int indexd = 0;

    public static void main(String[] args) throws Exception {

        long starttime = System.currentTimeMillis();

        int stopCnt = 99;

        Thread2_MultiTurn4 test4 = new Thread2_MultiTurn4();

        Thread threada = new Thread(test4.new childThread(stopCnt, 0));
        Thread threadb = new Thread(test4.new childThread(stopCnt, 1));
        Thread threadc = new Thread(test4.new childThread(stopCnt, 2));
        Thread threadd = new Thread(test4.new childThread(stopCnt, 3));

        test4.a[0] = 0;
        test4.b[0] = 1;
        test4.c[0] = 2;
        test4.d[0] = 3;

        threada.start();
        threadb.start();
        threadc.start();
        threadd.start();

        threada.join();
        threadb.join();
        threadc.join();
        threadd.join();

        long stoptime = System.currentTimeMillis();

        System.out.println("Time : " + (stoptime-starttime));

        System.out.println();
        for (int i = 0; i < 100; i++) {
            System.out.print(test4.a[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < 100; i++) {
            System.out.print(test4.b[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < 100; i++) {
            System.out.print(test4.c[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < 100; i++) {
            System.out.print(test4.d[i] + " ");
        }

    }

    class childThread implements Runnable {

        int selfCnt = 1;
        int stopCnt;
        int selfId;
        int nextInc;

        public childThread(int stopCnt, int selfId) {
            this.stopCnt = stopCnt;
            this.selfId = selfId;
            if (selfId != 0)
                nextInc = 1;
            else
                nextInc = -3;
        }

        @Override
        public void run() {
            process();
        }

        public void process() {
            while (true) {
                //System.out.println(selfId + " " + selfCnt);
                try {
                    if (selfId == (a[indexa] + nextInc)) {
                        if(indexa<99) {
                            indexa++;
                            a[indexa] = selfId;
                            selfCnt++;
                        }
                    }
                    if (selfId == (b[indexb] + nextInc)) {
                        if(indexb<99) {
                            indexb++;
                            b[indexb] = selfId;
                            selfCnt++;
                        }
                    }
                    if (selfId == (c[indexc] + nextInc)) {
                        if(indexc<99) {
                            indexc++;
                            c[indexc] = selfId;
                            selfCnt++;
                        }
                    }
                    if (selfId == (d[indexd] + nextInc)) {
                        if(indexd<99) {
                            indexd++;
                            d[indexd] = selfId;
                            selfCnt++;
                        }
                    }
                    if (selfCnt >= stopCnt){
                        System.out.println("====" + selfId);
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
        }

    }
}



