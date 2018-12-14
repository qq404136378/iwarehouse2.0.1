package com.prt.iwarehouse.model;



import com.prt.iwarehouse.http.HttpResult;
import com.prt.iwarehouse.http.RetrofitManager;
import com.prt.iwarehouse.pojo.Storage;
import com.prt.iwarehouse.pojo.StorageList;
import com.prt.iwarehouse.pojo.TokenUser;
import com.zzz.mvp.base.BaseModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by 锴锴兴 on 2018/12/11.
 */

public class UserModel extends BaseModel{

     public Observable<HttpResult<TokenUser>> doLogin(String userName, String password){
         return RetrofitManager
                 .getInstance()
                 .getRetrofitApiService()
                 .login(userName,password,"android");
     }
     public Observable<HttpResult<Object>> changeStorage(String uuid){
         return RetrofitManager
                 .getInstance()
                 .getRetrofitApiService()
                 .changeStorage(uuid);
     }
     public Observable<HttpResult<Storage>> getStorageList(){
         return  RetrofitManager
                 .getInstance()
                 .getRetrofitApiService()
                 .getStorageList();
     }
     //测试接口
    public Observable<HttpResult<Object>> test() {
        return RetrofitManager
                .getInstance()
                .getRetrofitApiService()
                .test();
    }

}
