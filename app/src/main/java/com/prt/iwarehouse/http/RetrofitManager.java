package com.prt.iwarehouse.http;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.prt.iwarehouse.BuildConfig;
import com.prt.iwarehouse.app.ApiService;
import com.prt.iwarehouse.app.AppContext;
import com.prt.iwarehouse.app.Constant;
import com.prt.iwarehouse.app.RecordLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.Utf8;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 请叫我张懂
 * @Date 2018/3/26 10:57
 * @Description
 */

public class RetrofitManager {
    //声明RetrofitApiService对象
    private ApiService retrofitApiService;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder builder;
    private static ArrayMap<String, Retrofit> retrofitCache;
    //由于该对象会被频繁调用，采用单例模式，下面是一种线程安全模式的单例写法（静态内部类）
    private static class SingletonHolder {
        private final static RetrofitManager instance = new RetrofitManager();
    }

    public static RetrofitManager getInstance() {
        return SingletonHolder.instance;
    }

    private RetrofitManager() {
        retrofitCache = new ArrayMap<>();
    }

    //初始化Retrofit
    public void init(String ip) {
        String baseUrl = Constant.BASE_URL_PRE + ip + Constant.BASE_URL_AFT;
        Retrofit retrofit = retrofitCache.get(baseUrl);
        if (null == retrofit) {
            //日志拦截器
            if (null == loggingInterceptor) {
                loggingInterceptor = new HttpLoggingInterceptor();
                if (BuildConfig.DEBUG) {
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                } else {
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
                }
            }
            if (null == builder) {
                builder = new OkHttpClient.Builder();
                builder.addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        RequestBody requestBody = request.body();
                        Buffer buffer = new Buffer();
                        if(requestBody!=null)
                        requestBody.writeTo(buffer);
                     //   RecordLog.getInstance().record(request.url().toString(),buffer.readString(Charset.forName("UTF-8")));
                        Request token = request.newBuilder()
                                .addHeader("XX-Device-Type", "android")
                                .addHeader("XX-Token",Constant.token)
                                .addHeader("XX-Api-Version","1")
                                .build();
                        return chain.proceed(token);
                    }
                }); builder.addInterceptor(loggingInterceptor);
                builder.connectTimeout(Constant.CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS);
                builder.readTimeout(Constant.READ_TIMEOUT_SEC, TimeUnit.SECONDS);
                builder.writeTimeout(Constant.WRITE_TIMEOUT_SEC, TimeUnit.SECONDS);
                builder.retryOnConnectionFailure(true);
            }
            //
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            retrofitCache.put(baseUrl, retrofit);
        }
        //进入API
        retrofitApiService = retrofit.create(ApiService.class);

    }
    public ApiService getRetrofitApiService() {

        return retrofitApiService;
    }
}
