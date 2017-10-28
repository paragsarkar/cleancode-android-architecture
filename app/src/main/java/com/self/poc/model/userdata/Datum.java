
package com.self.poc.model.userdata;


import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Datum {

    @SerializedName("user_address")
    private String mUserAddress;
    @SerializedName("user_id")
    private Long mUserId;
    @SerializedName("user_name")
    private String mUserName;

    public String getUserAddress() {
        return mUserAddress;
    }

    public void setUserAddress(String userAddress) {
        mUserAddress = userAddress;
    }

    public Long getUserId() {
        return mUserId;
    }

    public void setUserId(Long userId) {
        mUserId = userId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    @Override
    public String toString() {
        return "Datum{" +
                "mUserAddress='" + mUserAddress + '\'' +
                ", mUserId=" + mUserId +
                ", mUserName='" + mUserName + '\'' +
                '}';
    }
}
