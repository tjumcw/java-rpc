package com.mcw.rpc.server.provider.service;

import com.mcw.rpc.server.api.dto.UserDTO;
import com.mcw.rpc.server.api.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService, RpcService {

    private static final Map<String, UserDTO> userMap = new HashMap<>();

    static {
        userMap.put("mcw", new UserDTO("mcw", 18));
        userMap.put("xhy", new UserDTO("xhy", 18));
        userMap.put("hzh", new UserDTO("hzh", 18));
    }
    @Override
    public UserDTO queryUser(String name) {
        return userMap.getOrDefault(name, null);
    }
}
