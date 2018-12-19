package com.prt.iwarehouse.http;

/**
 * @author 请叫我张懂
 * @Date 2018/3/26 16:32
 * @Description
 */

public interface IHttpCallback<T> {
    void onSuccess(T t);

    void onError(String message);
}
