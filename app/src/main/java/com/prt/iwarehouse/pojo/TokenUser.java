package com.prt.iwarehouse.pojo;

/**
 * Created by 锴锴兴 on 2018/12/11.
 */

public class TokenUser {
    private String token;
    private User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "TokenUser{" +
                "token='" + token + '\'' +
                ", user=" + user +
                '}';
    }
}
