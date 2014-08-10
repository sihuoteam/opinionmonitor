package com.hhhy.common.utils;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class JsonUtils {

    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static void main(String[] args) {
        Map<String, String> s = new HashMap<String, String>();
        s.put("s1", "v1");
        s.put("s2", "v2");
        s.put("s3", "v3");
        System.out.println(JsonUtils.toJson(s));
        System.out.println(JsonUtils.toJson("haha"));
    }

}
