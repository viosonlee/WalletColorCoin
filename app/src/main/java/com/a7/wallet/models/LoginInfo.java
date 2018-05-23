package com.a7.wallet.models;

import com.google.gson.annotations.Expose;

/**
 * Created by viosonlee
 * on 2018/5/23.
 * for 登录信息
 */
public class LoginInfo {
    @Expose
    public String phone;
    @Expose
    public String password;

    public LoginInfo() {
    }

    public LoginInfo(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }
}
