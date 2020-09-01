package com.yyb.learn.character7;

import org.msgpack.annotation.Message;

/**
 * @Author yyb
 * @Description
 * @Date Create in 2020-09-01
 * @Time 15:40
 */
@Message
public class UserInfo {
    private String userName;
    private int age;

    public UserInfo() {
    }

    public UserInfo(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
