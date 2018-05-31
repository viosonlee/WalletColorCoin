package com.a7.wallet.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by viosonlee
 * on 2018/5/28.
 * for
 */
public class QrData implements Parcelable {
    @Expose
    @SerializedName("u")
    public String address;
    @Expose
    @SerializedName("n")
    public double amount;

    public QrData() {
    }

    protected QrData(Parcel in) {
        address = in.readString();
        amount = in.readDouble();
    }

    public static final Creator<QrData> CREATOR = new Creator<QrData>() {
        @Override
        public QrData createFromParcel(Parcel in) {
            return new QrData(in);
        }

        @Override
        public QrData[] newArray(int size) {
            return new QrData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeDouble(amount);
    }
}
