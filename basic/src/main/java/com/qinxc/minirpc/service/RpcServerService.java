package com.qinxc.minirpc.service;

import com.alibaba.fastjson.JSON;
import com.qinxc.minirpc.serde.JSONSerDe;
import com.qinxc.minirpc.serde.Serde;
import com.qinxc.minirpc.socket.SocketClient;


/**
 * @author qxc
 * @date 2018/11/29.
 */
public class RpcServerService {

    SocketClient socketClient;
    Serde serde = new JSONSerDe();

    public RpcServerService(SocketClient socketClient) {
        this.socketClient = socketClient;
    }

    public void service() {

        try {
            CallContract contract = (CallContract) serde.deser(socketClient.receive());
            contract.setRet(new Result("call result is filled"));
            socketClient.send(serde.ser(contract));
            System.out.println("RPC server get request, className:" + contract.getClassName() + " methodName:" + contract.getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
