package com.qinxc.minirpc;

import com.qinxc.minirpc.service.RpcServerService;
import com.qinxc.minirpc.socket.SocketClient;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author qxc
 * @date 2018/11/29.
 */
public class SocketServer {

    public void startServer(int portNumber) {

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Server started, listening on port:" + portNumber);
            while (true) {
                Socket client = serverSocket.accept();
                fixedThreadPool.execute(() -> {
                    RpcServerService service = new RpcServerService(new SocketClient(client));
                    service.service();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fixedThreadPool.shutdown();
        }
    }


    public static void main(String[] args) {
        new SocketServer().startServer(8989);
    }
}
