package com.a7.wallet.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by viosonlee
 * on 2018/5/28.
 * for
 */
public class QrData {
    @Expose
    @SerializedName("u")
    public String address;
    @Expose
    @SerializedName("n")
    public double amount;
}
