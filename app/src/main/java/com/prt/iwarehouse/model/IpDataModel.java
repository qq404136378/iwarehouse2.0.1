package com.prt.iwarehouse.model;


import android.content.Context;

import com.prt.iwarehouse.app.AppContext;
import com.prt.iwarehouse.app.Constant;
import com.zzz.mvp.base.BaseModel;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * @author 请叫我张懂
 * @Date 2018/3/29 10:07
 * @Description
 */

public class IpDataModel extends BaseModel {



    public Observable<Boolean> saveIpInfo(final String ip) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                boolean commit = AppContext.getInstance()
                        .getApplicationContext()
                        .getSharedPreferences("IpInfo", Context.MODE_PRIVATE)
                        .edit()
                        .putString("ip", ip)
                        .commit();
                emitter.onNext(commit);
                emitter.onComplete();
            }
        });
    }

    public Observable<String> readIpInfo() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String ip = AppContext.getInstance()
                        .getApplicationContext()
                        .getSharedPreferences("IpInfo", Context.MODE_PRIVATE)
                        .getString("ip", Constant.IP_HINT);
                emitter.onNext(ip);
                emitter.onComplete();
            }
        });
    }
}
