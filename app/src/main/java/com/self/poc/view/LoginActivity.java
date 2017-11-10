package com.self.poc.view;

import android.arch.lifecycle.LifecycleActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;

import com.self.core.locationHelper.GPSTracker;
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
    private FloatingActionButton floatingActionButton;
    private GPSTracker gpsTracker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gpsTracker = new GPSTracker(LoginActivity.this);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
        userPresentor = new UserPresentor(this,this);
        userPresentor.UserAllDataComingUp(userDataApi -> Log.e(TAG, "retriveTheData: "+userDataApi.toString() ));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data();
            }
        });
    }

    private void data(){
        if(gpsTracker.canGetLocation()){

        }

    }




}
