package com.prt.iwarehouse.model;

import android.content.Context;
import com.prt.baselibrary.utils.GsonUtil;
import com.prt.iwarehouse.app.AppContext;
import com.prt.iwarehouse.pojo.User;
import com.zzz.mvp.base.BaseModel;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * @author 请叫我张懂
 * @Date 2018/3/30 16:35
 * @Description
 */

public class FlowModel extends BaseModel {

    public <T> Observable<Boolean> clearFunctionFlow(final String account, final T t) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                boolean commit = AppContext.getInstance()
                        .getApplicationContext()
                        .getSharedPreferences(t.getClass().getSimpleName() + account, Context.MODE_PRIVATE)
                        .edit()
                        .putString("flow", "")
                        .commit();
                emitter.onNext(commit);
                emitter.onComplete();
            }
        });
    }
    public Observable<Boolean>  saveUserInfo(final String username, final String password){
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                boolean commit=AppContext.getInstance()
                        .getApplicationContext()
                        .getSharedPreferences("userInfo",Context.MODE_PRIVATE)
                        .edit()
                        .putString("username",username)
                        .putString("password",password)
                        .commit();
                emitter.onNext(commit);
                emitter.onComplete();
            }
        });
    }
    public Observable<User> redaUserInfo(){
        return  Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                String username=AppContext.getInstance()
                        .getApplicationContext()
                        .getSharedPreferences("userInfo",Context.MODE_PRIVATE)
                        .getString("username","");
                String password=AppContext.getInstance()
                        .getApplicationContext()
                        .getSharedPreferences("userInfo",Context.MODE_PRIVATE)
                        .getString("password","");
                User user=new User(username,password);
                emitter.onNext(user);
                emitter.onComplete();
            }
        });
    }
    public <T> Observable<Boolean> saveFunctionFlow(final String account, final T t) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                String json = GsonUtil.getInstance().toJson(t);
                boolean commit = AppContext.getInstance()
                        .getApplicationContext()
                        .getSharedPreferences(t.getClass().getSimpleName() + account, Context.MODE_PRIVATE)
                        .edit()
                        .putString("flow", json)
                        .commit();
                emitter.onNext(commit);
                emitter.onComplete();
            }
        });
    }

    public <T> Observable<T> readFunctionFlow(final String account, final Class<T> flowClazz) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                String json = AppContext.getInstance()
                        .getApplicationContext()
                        .getSharedPreferences(flowClazz.getSimpleName() + account, Context.MODE_PRIVATE)
                        .getString("flow", "");
                //
                T t = GsonUtil.getInstance().getBeanFromJson(json, flowClazz);
                if (t != null) {
                    emitter.onNext(t);
                }
                emitter.onComplete();
            }
        });
    }

}
