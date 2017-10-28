package com.self.poc.callback;

import com.self.poc.model.userdata.UserDataApi;

import java.util.List;

/**
 * Created by paragsarkar on 28/10/17.
 */

public interface UserPresentortoViewCallback {

    void retriveTheData(UserDataApi userDataApi);
}
