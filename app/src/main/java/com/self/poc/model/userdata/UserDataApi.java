
package com.self.poc.model.userdata;

import java.util.List;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class UserDataApi {

    @SerializedName("data")
    private List<Datum> mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("response")
    private String mResponse;

    public List<Datum> getData() {
        return mData;
    }

    public void setData(List<Datum> data) {
        mData = data;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getResponse() {
        return mResponse;
    }

    public void setResponse(String response) {
        mResponse = response;
    }

    @Override
    public String toString() {
        return "UserDataApi{" +
                "mData=" + mData +
                ", mMessage='" + mMessage + '\'' +
                ", mResponse='" + mResponse + '\'' +
                '}';
    }
}
