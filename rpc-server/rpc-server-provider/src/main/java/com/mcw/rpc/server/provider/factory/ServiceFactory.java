package com.mcw.rpc.server.provider.factory;

import com.mcw.rpc.server.api.service.UserService;
import com.mcw.rpc.server.provider.service.RpcService;
import com.mcw.rpc.server.provider.service.UserServiceImpl;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {
    private static final Map<Class, RpcService> serviceMap = new HashMap<>();

    static {
        serviceMap.put(UserService.class, new UserServiceImpl());
    }

    public RpcService getServiceInstance(Class clazz) {
        if (serviceMap.get(clazz) == null) {
            try {
                // 拿到的是UserService这个接口，没办法通过构造器得到它的实现类UserServiceImpl
                // 类似Spring的bean机制也是这样，你指定接口，回去找impl对应的类名去new
                // 或者直接通过一个ref -> 指向一个实现类的全限定类名，然后去new，所以接下来可以做的就是把这个动态配置做了
                // 干掉这个静态代码块，就可以实现所有的Service自动注册进这个ServiceFactory
                Constructor<? extends RpcService> constructor = clazz.getConstructor();
                RpcService rpcService = constructor.newInstance();
                serviceMap.put(clazz, rpcService);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return serviceMap.get(clazz);
    }
}
