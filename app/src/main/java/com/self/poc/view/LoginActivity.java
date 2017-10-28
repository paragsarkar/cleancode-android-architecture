package com.self.poc.view;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.self.poc.R;
import com.self.poc.callback.UserPresentortoViewCallback;
import com.self.poc.model.userdata.UserDataApi;
import com.self.poc.presentor.UserPresentor;

/**
 * Created by paragsarkar on 28/10/17.
 */

public class LoginActivity extends LifecycleActivity {

    private static final String TAG =LoginActivity.class.getSimpleName();
    private UserPresentor userPresentor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userPresentor = new UserPresentor(this,this);
        userPresentor.UserAllDataComingUp(new UserPresentortoViewCallback() {
            @Override
            public void retriveTheData(UserDataApi userDataApi) {
                Log.e(TAG, "retriveTheData: "+userDataApi.toString());
            }
        });
    }


}
