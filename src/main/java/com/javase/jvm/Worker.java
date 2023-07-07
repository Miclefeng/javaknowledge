package com.javase.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/6/30 09:50
 */
public class Worker {

    private Integer id;

    private String username;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static void printf(Worker w) {
        System.out.println(ClassLayout.parseInstance(w).toPrintable());
    }
}
