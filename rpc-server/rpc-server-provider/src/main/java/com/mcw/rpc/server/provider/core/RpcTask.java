package com.mcw.rpc.server.provider.core;

import com.mcw.rpc.server.api.request.RequestInfo;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class RpcTask implements Runnable {

    private Socket client;

    public RpcTask(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = client.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            // 接收客户端的请求反序列化
            RequestInfo requestInfo = (RequestInfo) objectInputStream.readObject();
            System.out.println("RpcTask -> requestInfo" + requestInfo);
            String className = requestInfo.getClassName();
            String methodName = requestInfo.getMethodName();
            Class[] paramsType = requestInfo.getParamsType();
            Object[] params = requestInfo.getParams();

            Class<?> clazz = Class.forName(className);
            Method method = clazz.getMethod(methodName, paramsType);
            Object result = method.invoke(RpcServer.serviceFactory.getServiceInstance(clazz), params);

            OutputStream outputStream = client.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
