package com.qinxc.minirpc.socket;

import java.net.Socket;

/**
 * @author qxc
 * @date 2018/11/29.
 */
public class SocketClient {

    private Socket client;

    public SocketClient(String host, int port) throws Exception {
        this.client = new Socket(host, port);
    }

    public SocketClient(Socket client) {
        this.client = client;
    }

    public void send(byte[] contract) {
        try {
            client.getOutputStream().write(contract);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] receive() {
        try {
            byte[] receive = new byte[1024];
            client.getInputStream().read(receive);
            return receive;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void shutDown() {
        try {
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client = null;
        }
    }
}