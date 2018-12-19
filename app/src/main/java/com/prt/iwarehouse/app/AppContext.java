package com.prt.iwarehouse.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.util.Log;

import com.prt.iwarehouse.R;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author 请叫我张懂
 * @Date 2018/3/18 15:15
 * @Description
 */

public class AppContext {
    private Context mContext;
    public static AppContext getInstance() {
        return InstanceHolder.instance;
    }

    void init(Context context) {
        mContext = context.getApplicationContext();
    }

    public Context getApplicationContext() {
        if (null == mContext) {
            throw new NullPointerException("AppContext must init in Application");
        }
        return mContext;
    }

    private static class InstanceHolder {

        private static final AppContext instance = new AppContext();

    }

    /**
     * 获取版本号
     *
     * @return
     */
    public String getVersion() {
        try {
            PackageInfo pi = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return mContext.getString(R.string.version_name);
        }
    }
}
