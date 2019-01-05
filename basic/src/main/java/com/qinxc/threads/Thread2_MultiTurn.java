package com.qinxc.threads;

public class Thread2_MultiTurn {

    Object threadLock = new Object();
    //volatile int globalId = 0;
    int globalId=0;

    public static void main(String[] args) throws Exception {

        Thread2_MultiTurn parent = new Thread2_MultiTurn();

        Thread threadA = new Thread(parent.new childThread("A", 10, 0));
        Thread threadB = new Thread(parent.new childThread("B", 10, 1));
        Thread threadC = new Thread(parent.new childThread("C", 10, 2));

        threadA.start();
        threadB.start();
        threadC.start();

        threadA.join();
        threadB.join();
        threadC.join();
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
        }

        public void process() {
            //synchronized (threadLock) {
                while (true) {
                    try {
                        while (selfId != globalId % 3) {
                            //threadLock.wait();
                        }
                        System.out.println("Name:" + this.name + "\tselfId:" + this.selfId + "\tselfCnt:" + selfCnt++);
                        globalId++;
                        //threadLock.notifyAll();
                        if (selfCnt == stopCnt)
                            break;
                    } catch (Exception e) {

                    }
                }
            //}

        }
    }


}


