package com.qinxc.jvm;

/**
 * @author qxc
 * @date 2019/2/22.
 */
public class TestClass {

    public void hot() {
        System.out.println(" version 1 : " + this.getClass().getClassLoader());
    }
}
