package com.qinxc.threads;

public class FourArray {

    public static void main(String[] args) throws InterruptedException {

        long timeStart = System.currentTimeMillis();

        processRecur pos = new processRecur();
        Thread tha = new Thread(() -> {
            pos.process(1);
        });
        Thread thb = new Thread(() -> {
            pos.process(2);
        });
        Thread thc = new Thread(() -> {
            pos.process(3);
        });
        Thread thd = new Thread(() -> {
            pos.process(0);
        });

        tha.start();
        thb.start();
        thc.start();
        thd.start();

        tha.join();
        thb.join();
        thc.join();
        thd.join();

        long timeEnd = System.currentTimeMillis();

        for(int i: pos.a) {
            System.out.print(i + " ");
        }
        System.out.println();
        for(int i: pos.b) {
            System.out.print(i + " ");
        }
        System.out.println();
        for(int i: pos.c) {
            System.out.print(i + " ");
        }
        System.out.println();
        for(int i: pos.d) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(timeEnd - timeStart);
    }

}

class processRecur {
    static int times = 100;
    int[] a = new int[times];
    int[] b = new int[times];
    int[] c = new int[times];
    int[] d = new int[times];
    int i = 1;
    int ai = 0, bi = -1, ci = -2, di = -3;

    public synchronized void process(int order) {
        while (true) {
            try {
                while (i % 4 != order) {
                    wait();
                }
                if (ai < times) {
                    a[ai++] = order;
                }
                if (bi >= 0 && bi < times) {
                    b[bi++] = order;
                } else {
                    bi++;
                }
                if (ci >= 0 && ci < times) {
                    c[ci++] = order;
                } else {
                    ci++;
                }
                if (di >= 0 && di < times) {
                    d[di++] = order;
                } else {
                    di++;
                }
                i++;
                notifyAll();
                if (di < times) {
                    wait();
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

