package com.prt.iwarehouse.modules.login;

import com.prt.iwarehouse.pojo.StorageList;
import com.prt.iwarehouse.pojo.User;
import com.zzz.mvp.base.IBaseView;

import java.util.List;

import io.reactivex.schedulers.Schedulers;

/**
 * Created by 锴锴兴 on 2018/12/11.
 */

interface LoginContract  {
    interface  ILoginView extends IBaseView{
       void LoginSuccess(String msg,String storageName);

       void LoginFail(String msg);

       void readIpInfoSuccess(String ip);

       void saveIpInfoSuccess(String ip);

       void saveIpInfoFail(String msg);

        void showStorageList(List<StorageList> storageList);

       void readUserInfoSuccess(User user);

       void changeStorageSuccess(String msg);

       void changeStorageFail(String msg);
    }
    interface  ILoginPresenter  {
       void toLogin(String userName,String password);

       void saveIpInfo(String ip);

       void readIpInfo();

       void saveUserInfo(String username,String password);

       void readUserInfo();

       void changeStorage(String storageUuid);
    }
}
