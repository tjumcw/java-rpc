package com.mcw.rpc.server.api.service;

import com.mcw.rpc.server.api.dto.UserDTO;

public interface UserService {
    UserDTO queryUser(String name);
}
