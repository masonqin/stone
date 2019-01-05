package com.qinxc.threads;

//version 1
//public class Thread1_Turn {
//
//    public static void main(String[] args) throws Exception {
//
//        Object threadLock = new Object();
//        Thread childThread = new Thread(() -> {
//            for (int i = 0; i < 5; i++) {
//                try {
//                    synchronized (threadLock) {
//                        for (int j = 0; j < 3; j++) {
//                            System.out.println("=====Child Thread:" + i + " " + j);
//                        }
//                        threadLock.notifyAll();
//                        if (i != 4)
//                            threadLock.wait();
//
//                    }
//                } catch (Exception e) {
//                }
//            }
//        });
//
//        childThread.start();
//        synchronized (threadLock) {
//            try {
//                threadLock.wait();
//            } catch (Exception e) {
//            }
//        }
//
//        for (int i = 0; i < 5; i++) {
//            synchronized (threadLock) {
//                for (int j = 0; j < 3; j++) {
//                    System.out.println("Parent Thread:" + i + " " + j);
//                }
//                try {
//                    threadLock.notifyAll();
//                    if (i != 4)
//                        threadLock.wait();
//                } catch (Exception e) {
//                }
//            }
//
//        }
//    }
//
//}

//version 2
public class Thread1_Turn {

    public static void main(String[] args) throws Exception {

        Process process = new Process();

        Thread child = new Thread(()->{
            process.sub();
        });
        child.start();
        process.major();

    }
}

class Process {

    int totalCnt = 5;
    int mainCnt = 6;
    int subCnt = 3;
    boolean doSub = false;

    public synchronized void sub() {
        //synchronized (threadLock) {
            while (true) {
                try {
                    while (!doSub)
                        wait();//threadLock.wait();
                    for (int i = 0; i < subCnt; i++) {
                        System.out.println("=====Child Thread:" + totalCnt + " " + i);
                    }
                    notifyAll();//threadLock.notifyAll();
                    doSub = false;
                    if (totalCnt-- > 0) {
                        wait();//threadLock.wait();
                    } else
                        break;
                } catch (Exception e) {
                }
            }

        //}
    }

    public synchronized void major() {
        //synchronized (threadLock) {
            while (true) {
                try {
                    while (doSub)
                        wait();//threadLock.wait();
                    for (int i = 0; i < mainCnt; i++) {
                        System.out.println("Parent Thread:" + totalCnt + " " + i);
                    }
                    notifyAll();//threadLock.notifyAll();
                    doSub = true;
                    if (totalCnt > 0) {
                        wait();//threadLock.wait();
                    } else
                        break;
                } catch (Exception e) {
                }
            }
        //}
    }

}

