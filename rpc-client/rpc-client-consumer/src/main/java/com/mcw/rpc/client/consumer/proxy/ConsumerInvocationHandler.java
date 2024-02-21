package com.mcw.rpc.client.consumer.proxy;

import com.mcw.rpc.server.api.request.RequestInfo;
import jdk.nashorn.internal.runtime.Scope;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConsumerInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RequestInfo requestInfo = new RequestInfo();
        // 通过需要代理的类，获得其全限定类名
        requestInfo.setClassName(proxy.getClass().getInterfaces()[0].getName());
        requestInfo.setMethodName(method.getName());
        requestInfo.setParams(args);
        requestInfo.setParamsType(method.getParameterTypes());

        // 通过socket发送请求信息（客户端无需指定），只需连接服务端
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 8899));
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(requestInfo);

        // 等待服务器响应
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Object result = objectInputStream.readObject();
        return result;
    }
}
