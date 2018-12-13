package com.prt.iwarehouse.pojo;

import java.util.List;

/**
 * Created by 锴锴兴 on 2018/12/11.
 */

public class TokenUser {
    private String token;
    private User user;
    private String storageName;
    private List<StorageList> storageList;

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

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public List<StorageList> getStorageList() {
        return storageList;
    }

    public void setStorageList(List<StorageList> storageList) {
        this.storageList = storageList;
    }

    @Override
    public String toString() {
        return "TokenUser{" +
                "token='" + token + '\'' +
                ", user=" + user +
                ", storageName='" + storageName + '\'' +
                ", storageList=" + storageList +
                '}';
    }
}
