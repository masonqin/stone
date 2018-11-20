package com.qinxc.basic;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author qxc
 * @date 2018/10/29.
 */
public class InvokeProxy {

    public static void main(String[] args) {
        BaseProxyCglib proxy = new BaseProxyCglib();
        BaseClass baseClassProxy = (BaseClass) proxy.getInstance(new BaseClass());
        baseClassProxy.baseMethod("medium");
        baseClassProxy.baseMethod2("medium2");
    }


}

class BaseClass {
    public void baseMethod(String outStr) {
        System.out.println("Base:" + outStr);
    }

    public void baseMethod2(String outStr) {
        System.out.println("Base2:" + outStr);
    }
}

class BaseProxyCglib implements MethodInterceptor {

    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();

    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        if(method.getName().contains("2")) {
            System.out.println("before");
        }
        result = methodProxy.invokeSuper(object, args);
        System.out.println("after");
        return result;
    }
}


