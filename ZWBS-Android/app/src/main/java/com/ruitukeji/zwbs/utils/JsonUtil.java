package com.ruitukeji.zwbs.utils;


import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/5/27.
 */
public class JsonUtil {
    private static JsonUtil jsonUtil = null;
    private static Gson gson;


    private JsonUtil() {
        gson = new Gson();
    }

    public synchronized static JsonUtil getInstance() {
        if (jsonUtil == null) {
            jsonUtil = new JsonUtil();
        }
        return jsonUtil;
    }


//    public static String map2JsonString(Map map) {
//
//        Gson gson = new Gson();
//
//        LogUtil.d("Tag", gson.toJson(map));
//        return gson.toJson(map);
//
//    }

    public static String obj2JsonString(Object obj) {
        return gson.toJson(obj);
    }


    public static Object json2Obj(String jsonString, Class clzz) {
        try {
            Object obj = gson.fromJson(jsonString, clzz);
            return obj;
        } catch (Exception e) {
            return null;
        }
    }

    public static Object json2ObjWithEx(String jsonString, Class clzz) throws Exception {
        Object obj = gson.fromJson(jsonString, clzz);
        return obj;
    }
}
