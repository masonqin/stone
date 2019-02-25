package com.qinxc.jvm;

import java.lang.reflect.Method;

/**
 * @author qxc
 * @date 2019/2/22.
 */
public class TestHotSwap {

    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new MonitorHotSwap());
        t.start();
        t.join();
    }

}

class MonitorHotSwap implements Runnable {

    private String className = "com.qinxc.jvm.TestClass";
    private Class hotClazz = null;
    private HotSwapURLClassLoader hotSwapCL = null;

    @Override
    public void run() {
        try {
            while (true) {
                initLoad();
                Object hot = hotClazz.newInstance();
                Method m = hotClazz.getMethod("hot");
                m.invoke(hot, null);
                Thread.sleep(3000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initLoad() throws Exception {
        hotSwapCL = HotSwapURLClassLoader.getClassLoader();
        hotClazz = hotSwapCL.loadClass(className);
    }
}
