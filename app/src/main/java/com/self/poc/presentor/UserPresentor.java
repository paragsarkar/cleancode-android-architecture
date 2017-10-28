package com.self.poc.presentor;


import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.self.poc.callback.UserPresentortoViewCallback;
import com.self.poc.model.provabdata.ProvabDataApi;
import com.self.poc.model.userdata.UserDataApi;
import com.self.poc.viewModel.UserLiveViewModel;

/**
 * Created by paragsarkar on 28/10/17.
 */

public class UserPresentor {

    public static final String TAG = UserPresentor.class.getCanonicalName();


    private Context context;
    private LifecycleActivity fragment;

    private UserLiveViewModel userLiveViewModel;
    private UserPresentortoViewCallback userPresentortoViewCallback;


    public UserPresentor(Context context, LifecycleActivity fragment) {
        this.context = context;
        this.fragment = fragment;
    }

    public void UserAllDataComingUp(final UserPresentortoViewCallback userPresentortoViewCallback){

        UserLiveViewModel.Factory factory= new UserLiveViewModel.Factory(context);
        userLiveViewModel = ViewModelProviders.of(fragment,factory).get(UserLiveViewModel.class);
        userLiveViewModel.init();
        userLiveViewModel.retriveValue().observe(fragment, new Observer<UserDataApi>() {
            @Override
            public void onChanged(@Nullable UserDataApi userDataApi) {
                userPresentortoViewCallback.retriveTheData(userDataApi);
            }
        });
    }


}
