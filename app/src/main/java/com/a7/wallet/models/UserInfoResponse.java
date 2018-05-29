package com.a7.wallet.models;

import com.google.gson.annotations.Expose;

import lee.vioson.network.core.BaseResponse;

/**
 * Created by viosonlee
 * on 2018/5/23.
 * for
 */
public class UserInfoResponse extends BaseResponse {
    @Expose public UserInfo user;

    @Override
    public String toString() {
        return "UserInfoResponse{" +
                "user=" + user +
                '}';
    }
}
