package com.self.poc.apiClientLevel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.self.poc.httpHandler.HttpHandler;
import com.self.poc.httpHandler.None;
import com.self.poc.model.userdata.UserDataApi;

import java.util.Map;

/**
 * Created by paragsarkar on 28/10/17.
 */

public class UserWebApi {

    private static final String TAG = UserWebApi.class.getSimpleName();


    private Context context;
    private UserDataApi userDataApi;



    public UserWebApi(Context context) {
        this.context = context;
    }

    public LiveData<UserDataApi> userDataApi() {
        final MutableLiveData<UserDataApi> userDataApiMutableLiveData = new MutableLiveData<>();
        HttpHandler<None, UserDataApi, UserDataApi> userHttpHandler = new HttpHandler<None, UserDataApi, UserDataApi>
                (HttpHandler.REQUEST_BODY_NONE, HttpHandler.RESPONSE_BODY_JSON_OBJECT, UserDataApi.class) {

            @Override
            public int getHttpRequestMethod() {
                return HTTP_GET;
            }

            @Override
            public String getURL() {
                //return "http://provabdev.in/android_system_task/api2.php";
                return "http://10.100.120.105:8000/user/";
            }

            @Override
            public void onResponse(UserDataApi result, Map<String, String> headers) {
                userDataApiMutableLiveData.postValue(result);
            }

        };
        userHttpHandler.executeWithTag(TAG);
        return userDataApiMutableLiveData;
    }

    public UserDataApi getUserDataApi() {
        return userDataApi;
    }

    public void setUserDataApi(UserDataApi userDataApi) {
        this.userDataApi = userDataApi;
    }

}
