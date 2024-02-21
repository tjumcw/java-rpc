package com.mcw.rpc.server.provider.core;

import com.mcw.rpc.server.provider.factory.ServiceFactory;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcServer {

    public static final ServiceFactory serviceFactory = new ServiceFactory();

    private static final ExecutorService threadPool = Executors.newFixedThreadPool(8);

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8899);
            while (true) {
                Socket client = server.accept();
                threadPool.execute(new RpcTask(client));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
