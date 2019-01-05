package com.qinxc.minirpc.proxy;

import com.qinxc.minirpc.serde.JSONSerDe;
import com.qinxc.minirpc.serde.Serde;
import com.qinxc.minirpc.service.CallContract;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.qinxc.minirpc.socket.SocketClient;

/**
 * @author qxc
 * @date 2018/11/29.
 */
public class RpcClientProxy implements java.lang.reflect.InvocationHandler {

    private Object object;
    private SocketClient socketClient;
    public Serde serde = new JSONSerDe();

    public RpcClientProxy(Object object, SocketClient socketClient) {
        this.object = object;
        this.socketClient = socketClient;
    }

    public static Object getProxy(Object object, SocketClient socketClient) {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), new RpcClientProxy(object, socketClient));
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {

        CallContract contract = new CallContract();
        contract.setClassName(method.getDeclaringClass().getName());
        contract.setMethodName(method.getName());
        contract.setArgs(args);
        socketClient.send(serde.ser(contract));
        return ((CallContract) serde.deser(socketClient.receive())).getRet();

    }

}
