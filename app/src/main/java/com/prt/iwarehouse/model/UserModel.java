package com.prt.iwarehouse.model;



import com.prt.iwarehouse.http.HttpResult;
import com.prt.iwarehouse.http.RetrofitManager;
import com.prt.iwarehouse.pojo.TokenUser;
import com.zzz.mvp.base.BaseModel;

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

     //测试接口
    public Observable<HttpResult<Object>> test() {
        return RetrofitManager
                .getInstance()
                .getRetrofitApiService()
                .test();
    }
}
