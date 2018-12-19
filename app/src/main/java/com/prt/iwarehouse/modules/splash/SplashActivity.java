package com.prt.iwarehouse.modules.splash;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Patterns;
import android.widget.TextView;
import android.widget.Toast;

import com.prt.iwarehouse.R;
import com.prt.iwarehouse.app.AppContext;
import com.prt.iwarehouse.app.Constant;
import com.prt.iwarehouse.http.RetrofitManager;
import com.prt.iwarehouse.modules.login.LoginActivity;
import com.prt.iwarehouse.modules.ip.IpContract;
import com.prt.iwarehouse.modules.ip.IpPresenter;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zzz.mvp.base.BaseMvpActivity;
import com.zzz.mvp.inject.InjectPresenter;

import java.lang.ref.WeakReference;

import io.reactivex.functions.Consumer;

/**
 * @author 请叫我张懂
 * @Date 2018/3/27 10:08
 * @Description
 */

public class SplashActivity extends BaseMvpActivity implements IpContract.IipView {
    private TextView tvVersionName;
    private long startTime;
    private long endTime;
    private MyHandler mHandler;
    public static final int MESSAGE_DIRECT = 121;//直接
    public static final int MESSAGE_INDIRECT = 1221;//间接
    //
    @InjectPresenter
    private IpPresenter ipPresenter;

    @Override
    protected int setContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        tvVersionName = this.findViewById(R.id.tv_version_name);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initData() {
        mHandler = new MyHandler(this);
        tvVersionName.setText(AppContext.getInstance().getVersion());
        //
        startTime = System.currentTimeMillis();
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            toLoginView();
                        } else {
                            Toast.makeText(SplashActivity.this, "请赋予权限", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                });
        ipPresenter.toReadIpInfo();
    }

    private void toLoginView() {
        endTime = System.currentTimeMillis();
        if (Constant.SPLASH_TIME_MM < endTime - startTime) {
            mHandler.sendEmptyMessage(MESSAGE_DIRECT);
        } else {
            mHandler.sendEmptyMessageDelayed(MESSAGE_INDIRECT, Constant.SPLASH_TIME_MM - (endTime - startTime));
        }
    }

    @Override
    public void saveIpInfoSuccess(String ip) {

    }

    @Override
    public void saveIpInfoFail(String message) {

    }

    @Override
    public void readIpInfoSuccess(String ip) {
        if (!Patterns.WEB_URL.matcher(ip).matches()) {
            ip = Constant.BASE_URL;
        }
        RetrofitManager.getInstance().init(ip);
    }

    private static class MyHandler extends Handler {
        WeakReference<Context> weakReference;

        MyHandler(Context context) {
            super(Looper.getMainLooper());
            weakReference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            if (weakReference != null && weakReference.get() != null) {
                Context context = weakReference.get();
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        }
    }
}
