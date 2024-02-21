package com.mcw.rpc.client.consumer;

import com.mcw.rpc.client.consumer.proxy.ConsumerInvocationHandler;
import com.mcw.rpc.server.api.dto.UserDTO;
import com.mcw.rpc.server.api.service.UserService;

import java.lang.reflect.Proxy;

public class Task {
    // 已经引入了api接口的jar包，需要通过这个接口远程调用
    // 实际上需要做的是包装一个请求信息，通过socket发送给rpc-server端，并且通过socket拿到结果
    // 但是这个过程对于客户端（或者说是使用者）应该是无感知的，所以一般通过动态代理
    // 这样就通过最基本的socket + 动态代理 + 反射实现了RPC

    public static void main(String[] args) {
        UserService userService = (UserService) Proxy.newProxyInstance(
                UserService.class.getClassLoader(), new Class[]{UserService.class}, new ConsumerInvocationHandler());
        UserDTO mcw = userService.queryUser("mcw");
        System.out.println(mcw);
    }
}
