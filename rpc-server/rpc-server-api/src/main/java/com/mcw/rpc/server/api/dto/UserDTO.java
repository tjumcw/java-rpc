package com.mcw.rpc.server.api.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = -7450560621039548537L;

    private String name;
    private Integer age;

    public UserDTO(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
