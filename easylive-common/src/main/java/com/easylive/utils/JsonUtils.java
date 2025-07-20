package com.easylive.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
public class JsonUtils {
    public static String convertObjToJson(Object obj){
        return JSON.toJSONString(obj);
    }
    public static <T> T convertJsonToObj(String json,Class<T> classz){
        return JSONObject.parseObject(json,classz);
    }
    public static <T> List<T> convertJsonArrayToList(String json, Class<T> classz){
        return JSONArray.parseArray(json,classz);
    }
    public static void main(String[] args) {

    }
}
