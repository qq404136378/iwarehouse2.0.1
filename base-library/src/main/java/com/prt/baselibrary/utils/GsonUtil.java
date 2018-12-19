package com.prt.baselibrary.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 请叫我张懂 on 2017/6/20.
 */

public class GsonUtil {
    private static GsonUtil instance;
    private Gson gson;

    private GsonUtil() {
        gson = new Gson();
    }

    public static GsonUtil getInstance() {
        if (instance == null) {
            synchronized (GsonUtil.class) {
                if (instance == null) {
                    instance = new GsonUtil();
                }
            }
        }
        return instance;
    }

    public String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public <T> T getBeanFromJson(String json, Class<T> clazz) {
        T t = gson.fromJson(json, clazz);
        return t;
    }

    public <T> T getBeanFromJson(String json, Type type) {
        T t = gson.fromJson(json, type);
        return t;
    }

    public <T> Map<String, T> getMapFromJson(String json) {
        Map<String, T> map = gson.fromJson(json, new TypeToken<Map<String, T>>() {
        }.getType());
        return map;
    }

    public <T> List<T> GsonToList(String json, Class<T> cls) {
        ArrayList<T> mList = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            mList.add(gson.fromJson(elem, cls));
        }
        return mList;
    }
}
