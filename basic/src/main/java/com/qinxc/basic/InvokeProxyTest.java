package com.qinxc.basic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author qxc
 * @date 2018/11/30.
 */
public class InvokeProxyTest {

    public static void main(String[] args) {
        /* 设置此系统属性,让JVM生成的Proxy类写入文件.保存路径为：com/sun/proxy(如果不存在请手工创建) */
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        System.out.println(Proxy.getProxyClass(IUser.class.getClassLoader(), IUser.class));
        IUser userImpl = (IUser) new DynamicProxy().bind(new UserImpl());
        System.out.println(userImpl.sayHello(" kevin LUAN"));
    }

    public static class DynamicProxy implements InvocationHandler {
        public Object target;

        public Object bind(Object target) {
            this.target = target;
            return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(target, args);
        }
    }

    public static interface IUser {
        public String sayHello(String speakString);
    }

    public static class UserImpl implements IUser {

        @Override
        public String sayHello(String speakString) {
            return "welcome " + speakString;
        }

    }
}


