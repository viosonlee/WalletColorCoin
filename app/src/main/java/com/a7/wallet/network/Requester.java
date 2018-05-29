package com.a7.wallet.network;

import android.content.Context;

import com.a7.wallet.App;
import com.a7.wallet.models.PayLogResponse;
import com.a7.wallet.models.UserInfo;
import com.a7.wallet.models.UserInfoResponse;

import lee.vioson.network.core.BaseObserver;
import lee.vioson.network.core.BaseResponse;
import lee.vioson.network.core.RequestFactory;

/**
 * Created by viosonlee
 * on 2018/5/23.
 * for
 */
public class Requester {
    private static final String IP = "http://120.78.254.180";

    private static ApiService getApi(Context context) {
        return RequestFactory.<ApiService>getRequester(IP, ApiService.class)
                .getAPIForJsonSaveCookies(context);
    }

    public static void sendVCode(String tel, int type, BaseObserver handler) {
        RequestFactory.request(getApi(App.APP_CONTEXT).sendCodeToTel(tel, type), handler);
    }

    public static void checkVCode(String tel, int vCode, BaseObserver<BaseResponse> handler) {
        RequestFactory.request(getApi(App.APP_CONTEXT).checkVCode(tel, vCode), handler);
    }

    public  static void register(String accountName, String password, String email, String nickName, String headUrl, String sex,  String name, String idCard, String phone,
                         String vCode, String suffix, String imageBase64, String gameCoreVersion, BaseObserver handler) {
        RequestFactory.request(getApi(App.APP_CONTEXT).register(accountName, password, email, nickName, headUrl, sex, name, idCard,
                phone, vCode, suffix, imageBase64, gameCoreVersion), handler);
    }

    public  static void login(String user, String password, int gameVersion, BaseObserver<UserInfoResponse> handler) {
        RequestFactory.request(getApi(App.APP_CONTEXT).login(user, password, gameVersion), handler);
    }

    public static void logout(BaseObserver<BaseResponse> handler) {
        RequestFactory.request(getApi(App.APP_CONTEXT).logout(), handler);
    }

    public static void editHeadPic(long userID, String suffix, String picBase64, BaseObserver<BaseResponse> handler) {
        RequestFactory.request(getApi(App.APP_CONTEXT).editHeadPic(userID, suffix, picBase64), handler);
    }


    public static void editPassword(String tel, String oldPwd, String type, String pwd, String smsCode, BaseObserver handler) {
        RequestFactory.request(getApi(App.APP_CONTEXT).editPassword(tel, oldPwd, type, pwd, smsCode), handler);
    }

    public void editByPlayer(String userId, String userNickName, String userHeader, String suffix,
                             String picBase64, BaseObserver<UserInfoResponse> handler) {
        RequestFactory.request(getApi(App.APP_CONTEXT).editByPlayer(userId, userNickName, userHeader, suffix, picBase64), handler);
    }

    public static void userInfo(long userId, BaseObserver<UserInfo> handler) {
        RequestFactory.request(getApi(App.APP_CONTEXT).userInfo(userId), handler);
    }

    public static void findUserByWallet(String walletAddr, BaseObserver<UserInfoResponse> handler) {
        RequestFactory.request(getApi(App.APP_CONTEXT).findUserByWallet(walletAddr), handler);
    }

    public static void exchangeDiamondCoin(String userId, String toUserID, double amount, BaseObserver<BaseResponse> handler) {
        RequestFactory.request(getApi(App.APP_CONTEXT).exchangeDiamondCoin(userId, toUserID, amount), handler);
    }

    public static void findPayLog(String userId, String userId1, int type, int moneyType, int pageNumber, BaseObserver<PayLogResponse> handler) {
        RequestFactory.request(getApi(App.APP_CONTEXT).findPayLog(userId, userId1, type, moneyType, pageNumber), handler);
    }

    public static  void exchangeDiamondCoinByAddress(String walletAddr, String toWalletAddr, double amount, BaseObserver<BaseResponse> handler) {
        RequestFactory.request(getApi(App.APP_CONTEXT).exchangeDiamondCoin1(walletAddr, toWalletAddr, amount), handler);
    }

    public static void findPayLogByAddress(String walletAddr, String toWalletAddr, int type, int moneyType, int pageNumber, BaseObserver<PayLogResponse> handler) {
        RequestFactory.request(getApi(App.APP_CONTEXT).findPayLog1(walletAddr, toWalletAddr, type, moneyType, pageNumber), handler);
    }
}
