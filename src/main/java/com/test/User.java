package com.test;

import java.io.Serializable;

/**
 * @Author: miclefengzss
 * @Date: 2024/3/1 09:57
 */
public class User implements Serializable {


    private static final long serialVersionUID = -6232469419104796000L;
    private Integer id;
    private String name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
