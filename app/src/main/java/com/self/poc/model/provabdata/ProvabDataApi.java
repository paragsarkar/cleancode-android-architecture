
package com.self.poc.model.provabdata;


import com.google.gson.annotations.SerializedName;


public class ProvabDataApi {

    @SerializedName("description")
    private String mDescription;
    @SerializedName("first_name")
    private String mFirstName;
    @SerializedName("last_name")
    private String mLastName;
    @SerializedName("user_id")
    private String mUserId;
    @SerializedName("user_image")
    private String mUserImage;
    @SerializedName("user_name")
    private String mUserName;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public String getUserImage() {
        return mUserImage;
    }

    public void setUserImage(String userImage) {
        mUserImage = userImage;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    @Override
    public String toString() {
        return "ProvabDataApi{" +
                "mDescription='" + mDescription + '\'' +
                ", mFirstName='" + mFirstName + '\'' +
                ", mLastName='" + mLastName + '\'' +
                ", mUserId='" + mUserId + '\'' +
                ", mUserImage='" + mUserImage + '\'' +
                ", mUserName='" + mUserName + '\'' +
                '}';
    }
}
