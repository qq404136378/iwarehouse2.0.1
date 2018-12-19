package com.prt.iwarehouse.app;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {

        super.onCreate();
        AppContext.getInstance().init(this);
    }


}
