package com.prt.iwarehouse.pojo;

import java.util.List;

/**
 * Created by 锴锴兴 on 2018/12/14.
 */

public class Storage {
    private List<StorageList> storageList;

    public List<StorageList> getStorageList() {
        return storageList;
    }

    public void setStorageList(List<StorageList> storageList) {
        this.storageList = storageList;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "storageList=" + storageList +
                '}';
    }
}
