package com.prt.iwarehouse.app;

import com.prt.iwarehouse.http.HttpResult;
import com.prt.iwarehouse.pojo.Storage;
import com.prt.iwarehouse.pojo.StorageList;
import com.prt.iwarehouse.pojo.TokenUser;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {

    @FormUrlEncoded
    @POST(Constant.url.LOGIN)
    Observable<HttpResult<TokenUser>> login(@Field("username") String username,
                                            @Field("password") String password,
                                            @Field("device_type")String device_type);

    @PUT(Constant.url.CHANGE_STORAGE)
    Observable<HttpResult<Object>> changeStorage(@Path("uuid") String uuid);

    @GET(Constant.url.TEST)
    Observable<HttpResult<Object>> test();



    @GET(Constant.url.GET_STORAGE_LIST)
    Observable<HttpResult<Storage>> getStorageList();
}
