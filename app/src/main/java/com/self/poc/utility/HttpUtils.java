package com.self.poc.utility;

import com.google.gson.Gson;

/**
 * Created by paragsarkar on 19/10/17.
 */

public class HttpUtils {

    public static Gson gson = new Gson();

    public static <T> T deserialize(String obj, Class<T> tClass) {
        return gson.fromJson(obj, tClass);
    }

}
