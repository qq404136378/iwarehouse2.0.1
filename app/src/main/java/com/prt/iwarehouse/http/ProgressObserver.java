package com.prt.iwarehouse.http;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.prt.iwarehouse.app.AppContext;
import com.prt.iwarehouse.app.Constant;
import com.prt.iwarehouse.app.RecordLog;
import org.json.JSONException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 如果泛型继承于HttpResult 判断result字段是否为success
 * 通过subscribe订阅观察者
 *
 * @param <T>
 */
public class ProgressObserver<T> implements Observer<T>, ProgressCancelListener {
    private IHttpCallback<T> mHttpCallback;
    private Disposable disposable;
    private Context context;
    private Boolean isShowProgress;

    public ProgressObserver(Context context, boolean isShowProgress, IHttpCallback<T> httpCallback) {
        this.mHttpCallback = httpCallback;
        this.context=context;
        this.isShowProgress=isShowProgress;
    }

    private void showProgressDialog() {
        Intent intent=new Intent("PROGRESS_DIALOG");
        intent.putExtra("isShowDialog",true);
        AppContext.getInstance().getApplicationContext()
                .sendBroadcast(intent);
    }
    private void dismissProgressDialog() {
        Intent intent=new Intent("PROGRESS_DIALOG");
        intent.putExtra("isShowDialog",false);
        AppContext.getInstance().getApplicationContext()
                .sendBroadcast(intent);
    }


    @Override
    public void onSubscribe(Disposable d) {
        this.disposable = d;
        if(isShowProgress)
        showProgressDialog();
    }

    @Override
    public void onNext(final T t) {
        if(isShowProgress)
            dismissProgressDialog();
       if(mHttpCallback!=null) {
           if (((HttpResult) t).getCode().equals("100"))
               mHttpCallback.onError(((HttpResult) t).getMsg());
           else
               mHttpCallback.onSuccess(t);
       }
    }

    @Override
    public void onComplete() {
        if(isShowProgress)
        dismissProgressDialog();

    }
    @Override
    public void onError(Throwable e) {
        String result = "未知错误";
        if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            result = "网络中断，请检查您的网络状态";
        } else if (e instanceof HttpException) {
            int code = ((HttpException) e).code();
            result = "HTTP错误,code:" + code;
        } else if (e instanceof SocketTimeoutException) {
            result = "连接超时";
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            result = "解析错误";
        } else {
            if (e != null) {
                e.printStackTrace();
                result = e.toString();
            }
        }

     //   RecordLog.getInstance().record(e);
        if(isShowProgress)
            dismissProgressDialog();
        if (mHttpCallback != null) {
            mHttpCallback.onError(result);
        }

    }


    @Override
    public void onCancelProgress() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

}
