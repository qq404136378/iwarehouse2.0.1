package com.prt.iwarehouse.modules.login;

import com.prt.iwarehouse.app.Constant;
import com.prt.iwarehouse.http.HttpResult;
import com.prt.iwarehouse.http.IHttpCallback;
import com.prt.iwarehouse.http.ProgressObserver;
import com.prt.iwarehouse.model.FlowModel;
import com.prt.iwarehouse.model.IpDataModel;
import com.prt.iwarehouse.model.UserModel;
import com.prt.iwarehouse.pojo.TokenUser;
import com.prt.iwarehouse.pojo.User;
import com.zzz.mvp.base.BasePresenter;
import com.zzz.mvp.inject.InjectModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 锴锴兴 on 2018/12/11.
 */

public class LoginPresenter extends BasePresenter<LoginContract.ILoginView>
        implements LoginContract.ILoginPresenter  {
    @InjectModel
    UserModel userModel;
    @InjectModel
    IpDataModel ipDataModel;
    @InjectModel
    FlowModel flowModel;
    @Override
    public void toLogin(String userName, String password) {
       userModel.doLogin(userName,password)
               .subscribeOn(Schedulers.io())
               .unsubscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressObserver<HttpResult<TokenUser>>(getContext(), true,
                       new IHttpCallback<HttpResult<TokenUser>>() {
                   @Override
                   public void onSuccess(HttpResult<TokenUser> tokenUserHttpResult) {
                       Constant.token=tokenUserHttpResult.getData().getToken();
                     if(!tokenUserHttpResult.getData().getStorageName().equals("")) {
                         getView().LoginSuccess(tokenUserHttpResult.getMsg(),tokenUserHttpResult.getData().getStorageName());
                     }else{
                         if(tokenUserHttpResult.getData().getStorageList()!=null){
                             getView().showStorageList(tokenUserHttpResult.getData().getStorageList());
                         }
                     }
                   }

                   @Override
                   public void onError(String message) {
                       getView().LoginFail(message);
                   }
               }));
    }

    @Override
    public void saveIpInfo(final String ip) {
        ipDataModel.saveIpInfo(ip)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if(aBoolean){
                              getView().saveIpInfoSuccess(ip);
                        }else {
                            getView().saveIpInfoFail("IP保存失败");
                        }
                    }
                });
    }

    @Override
    public void readIpInfo() {
          ipDataModel.readIpInfo()
                  .subscribeOn(Schedulers.io())
                  .unsubscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Consumer<String>() {
                      @Override
                      public void accept(String s) throws Exception {
                          getView().readIpInfoSuccess(s);
                      }
                  });
    }

    @Override
    public void saveUserInfo(String username, String password) {
        flowModel.saveUserInfo(username,password)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {

                    }
                });
    }

    @Override
    public void readUserInfo() {
        flowModel.redaUserInfo()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        getView().readUserInfoSuccess(user);
                    }
                });
    }
     @Override
    public  void changeStorage(String storageUuid){
        userModel.changeStorage(storageUuid)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressObserver<HttpResult<Object>>(getContext(), true, new IHttpCallback<HttpResult<Object>>() {
                    @Override
                    public void onSuccess(HttpResult<Object> objectHttpResult) {
                       getView().changeStorageSuccess(objectHttpResult.getMsg());
                    }

                    @Override
                    public void onError(String message) {
                       getView().changeStorageFail(message);
                    }
                }));
    }

}
