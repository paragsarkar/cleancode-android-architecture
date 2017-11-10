package com.self.poc.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.self.poc.apiClientLevel.UserWebApi;
import com.self.poc.model.userdata.UserDataApi;

/**
 * Created by paragsarkar on 28/10/17.
 */

public class UserRepositiry {

    private Context context;
    private UserWebApi userWebApi;

    private LiveData<UserDataApi> userDataApiLiveData;
    private UserDataApi userDataApi;

    public UserRepositiry(Context context) {
        this.context = context;
        userWebApi = new UserWebApi(context);
    }

    public LiveData<UserDataApi> userDataApi(){
        userDataApiLiveData =  userWebApi.userDataApi();
        return userDataApiLiveData;
    }
}
