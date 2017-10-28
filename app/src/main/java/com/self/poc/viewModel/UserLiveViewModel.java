package com.self.poc.viewModel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;

import com.self.poc.model.provabdata.ProvabDataApi;
import com.self.poc.model.userdata.UserDataApi;
import com.self.poc.repository.UserRepositiry;

import java.util.List;

/**
 * Created by paragsarkar on 28/10/17.
 */

public class UserLiveViewModel extends ViewModel {

    private LiveData<UserDataApi> userLiveViewModels;
    private UserRepositiry userRepositiry;
    private UserDataApi userDataApi;
    private Context context;
    MutableLiveData<UserDataApi> userDataApiMutableLiveData;


    public UserLiveViewModel(Context context) {
        this.context = context;
        userRepositiry = new UserRepositiry(context);
        userDataApiMutableLiveData = new MutableLiveData<>();
    }

    public void init(){
        if(userLiveViewModels==null){
            userLiveViewModels = userRepositiry.userDataApi();
        }
    }

    public LiveData<UserDataApi> retriveValue(){
        return userLiveViewModels;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Context mApplication;


        public Factory(@NonNull Context application) {
            mApplication = application;

        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new UserLiveViewModel(mApplication);
        }
    }
}
