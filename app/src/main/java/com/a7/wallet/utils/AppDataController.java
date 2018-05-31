package com.a7.wallet.utils;

import android.text.TextUtils;


import com.a7.wallet.App;
import com.a7.wallet.models.LoginInfo;
import com.a7.wallet.models.UserInfo;
import com.a7.wallet.models.UserInfoResponse;
import com.a7.wallet.network.DataCallBack;
import com.a7.wallet.network.Requester;
import com.a7.wallet.views.acitivities.LoginActivity;

import lee.vioson.network.core.BaseApiException;
import lee.vioson.network.utils.JSONUtils;
import lee.vioson.network.utils.SpUtil;

/**
 * Created by viosonlee
 * on 2017/10/28.
 * for app数据
 */

public class AppDataController {
    private static final String APP_DATA_TABLE = "com.a7.wallet";

    private static final String USER_INFO = "user_info";
    private static final String AUTO_LOGIN = "auto_login";
    private static final String LOGIN_INFO = "login_info";
    private static final String SAFE_PASSWORD = "safe_password";

    public static UserInfo getUserInfo() {
        String string = SpUtil.with(App.APP_CONTEXT)
                .getString(APP_DATA_TABLE, USER_INFO);
        if (!TextUtils.isEmpty(string))
            return JSONUtils.fromJson(string, UserInfo.class);
        else return new UserInfo();
    }

    public static void saveUserInfo(UserInfo userInfo) {
        if (userInfo == null)
            SpUtil.with(App.APP_CONTEXT)
                    .saveString(APP_DATA_TABLE, USER_INFO, "");
        else
            SpUtil.with(App.APP_CONTEXT)
                    .saveString(APP_DATA_TABLE, USER_INFO, JSONUtils.toJson(userInfo));
    }

    public static LoginInfo getLoginInfo() {
        String string = SpUtil.with(App.APP_CONTEXT)
                .getString(APP_DATA_TABLE, LOGIN_INFO);
        if (!TextUtils.isEmpty(string))
            return JSONUtils.fromJson(string, LoginInfo.class);
        else return new LoginInfo();
    }

    public static void saveLoginInfo(LoginInfo loginInfo) {
        if (loginInfo == null)
            SpUtil.with(App.APP_CONTEXT)
                    .saveString(APP_DATA_TABLE, LOGIN_INFO, "");
        else SpUtil.with(App.APP_CONTEXT)
                .saveString(APP_DATA_TABLE, LOGIN_INFO, JSONUtils.toJson(loginInfo));
    }

    public static boolean isLogin() {
        return !TextUtils.isEmpty(SpUtil.with(App.APP_CONTEXT).getString(APP_DATA_TABLE, USER_INFO));
    }


    public static void setAutoLogin(boolean autoLogin) {
        SpUtil.with(App.APP_CONTEXT).saveBoolean(APP_DATA_TABLE, AUTO_LOGIN, autoLogin);
    }

    public static boolean isAutoLogin() {
        return SpUtil.with(App.APP_CONTEXT).getBoolean(APP_DATA_TABLE, AUTO_LOGIN, false);
    }

    public static boolean hasPayPwd() {
        return getUserInfo().getPayPwdBind() == 1;
    }

    public static void setHasPayPasswordBind(boolean has) {
        UserInfo userInfo = getUserInfo();
        userInfo.setPayPwdBind(has ? 1 : 0);
        saveUserInfo(userInfo);
    }

    public static void logout() {
        saveUserInfo(null);
    }

    public static void saveSafePassWord(String password) {
        SpUtil.with(App.APP_CONTEXT)
                .saveString(APP_DATA_TABLE, SAFE_PASSWORD, password);
    }

    public static String getSafePassword() {
        return SpUtil.with(App.APP_CONTEXT).getString(APP_DATA_TABLE, SAFE_PASSWORD);
    }

    public static void refreshToken() {
        Requester.login(getLoginInfo().phone, getLoginInfo().password, 1, new DataCallBack<UserInfoResponse>() {
            @Override
            protected void onHandleSuccess(UserInfoResponse data) {
                saveUserInfo(data.user);
            }

            @Override
            protected void onHandleError(BaseApiException e) {
                super.onHandleError(e);
                logout();
                LoginActivity.reLogin(App.APP_CONTEXT);
            }
        });
    }
}
