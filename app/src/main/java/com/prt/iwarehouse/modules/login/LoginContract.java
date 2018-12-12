package com.prt.iwarehouse.modules.login;

import com.prt.iwarehouse.pojo.User;
import com.zzz.mvp.base.IBaseView;

import io.reactivex.schedulers.Schedulers;

/**
 * Created by 锴锴兴 on 2018/12/11.
 */

interface LoginContract  {
    interface  ILoginView extends IBaseView{
       void LoginSuccess(String msg);

       void LoginFail(String msg);

       void readIpInfoSuccess(String ip);

       void saveIpInfoSuccess(String ip);

       void saveIpInfoFail(String msg);


       void readUserInfoSuccess(User user);
    }
    interface  ILoginPresenter  {
       void toLogin(String userName,String password);

       void saveIpInfo(String ip);

       void readIpInfo();

       void saveUserInfo(String username,String password);

       void readUserInfo();
    }
}
