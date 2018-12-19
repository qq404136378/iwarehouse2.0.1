package com.prt.iwarehouse.app;


import com.prt.iwarehouse.pojo.StorageList;
import com.prt.iwarehouse.pojo.User;

import io.reactivex.schedulers.Schedulers;

/**
 * @author 请叫我张懂
 * @Date 2018/3/26 13:46
 * @Description
 */

public class Constant {
   public static final String CODE_RECEIVER_ACTION = "android.intent.action.SCANRESULT";
    public static final String IP_HINT = "请输入IP";
    public static final String BASE_URL_PRE = "http://";
    public static final String BASE_URL_AFT = "/";
    public static final String BASE_URL = "10.10.10.0";
    //
    //网络参数100
    public static final int CONNECT_TIMEOUT_SEC = 3;
    public static final int READ_TIMEOUT_SEC = 3;
    public static final int WRITE_TIMEOUT_SEC = 3;
    //
    public static final int SPLASH_TIME_MM = 1250;//闪屏时间
    //
    public static String storageName;
    public static String token="";
    public static User user;

    static class  url{
       //登陆url
       static final String LOGIN="public/api/storage/user/login";
       static final String CHANGE_STORAGE="public/api/storage/storage/updateCurrStorage/{uuid}";
       static final String GET_STORAGE_LIST="public/api/storage/storage";
       //test
       static final String TEST="public/api/storage/preparations/test";

    }
}
