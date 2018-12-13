package com.prt.iwarehouse.pojo;

/**
 * Created by 锴锴兴 on 2018/12/13.
 */

public class StorageList {
    private String storageUuid;
    private String name;

    public String getStorageUuid() {
        return storageUuid;
    }

    public void setStorageUuid(String storageUuid) {
        this.storageUuid = storageUuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StorageList{" +
                "storageUuid='" + storageUuid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
