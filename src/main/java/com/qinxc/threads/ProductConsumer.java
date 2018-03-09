package com.qinxc.threads;

public class ProductConsumer {


    public static void main(String[] args) {
        Resource r = new Resource();

        Producer p = new Producer(r);
        Consumer c = new Consumer(r);

        Thread t1 = new Thread(p);
        Thread t2 = new Thread(c);

        Thread t3 = new Thread(p);
        Thread t4 = new Thread(c);

        Thread t5 = new Thread(p);
        Thread t6 = new Thread(c);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }

}

class Resource {
    private String name;
    private int count = 1;
    private boolean flag = false;

    public synchronized void set(String name) {
        while (flag) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        this.name = name + "---" + count++;
        System.out.println(Thread.currentThread().getName()
                + "...Producer..." + this.name);
        if (count < 50) {
            flag = true;
            this.notifyAll();
        } else {
            System.exit(0);
        }
    }

    public synchronized void out() {
        while (!flag) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        System.out.println(Thread.currentThread().getName()
                + "...Consumer..." + this.name);
        if (count < 50) {
            flag = false;
            this.notifyAll();
        } else {
        }
    }
}

class Producer implements Runnable {

    private Resource res;

    Producer(Resource res) {
        this.res = res;
    }

    @Override
    public void run() {
        while (true) {
            res.set("item");
        }
    }
}

class Consumer implements Runnable {
    private Resource res;

    Consumer(Resource res) {
        this.res = res;
    }

    @Override
    public void run() {
        while (true) {
            res.out();
        }
    }
}







