package com.mortuza.digitalsquaremed.model.network;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResponseWrapperArray<T,S> implements Serializable {

    @SerializedName("message")
    String message;

    @SerializedName("status")
    boolean status;

    @SerializedName("appPermissionArray")
    List<T> appPermissionArray=null;

    @SerializedName("employeeAppPermissionList")
    List<S> appData=null;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getAppPermissionArray() {
        return appPermissionArray;
    }

    public void setAppPermissionArray(List<T> appPermissionArray) {
        this.appPermissionArray = appPermissionArray;
    }

    public List<S> getAppData() {
        return appData;
    }

    public void setAppData(List<S> appData) {
        this.appData = appData;
    }
}
