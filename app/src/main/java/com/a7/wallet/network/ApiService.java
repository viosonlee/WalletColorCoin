package com.a7.wallet.network;

import com.a7.wallet.models.EditHeadPicResponse;
import com.a7.wallet.models.MatherResponse;
import com.a7.wallet.models.PayLogResponse;
import com.a7.wallet.models.UserInfo;
import com.a7.wallet.models.UserInfoResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by viosonlee
 * on 2018/5/23.
 * for
 */
public interface ApiService {
    @FormUrlEncoded
    @POST("/user/sendCodeToTel")
    Observable<MatherResponse> sendCodeToTel(@Field("tel") String tel, @Field("type") int type);

    @FormUrlEncoded
    @POST("/user/checkSmsCode")
        /*校验验证码*/
    Observable<MatherResponse> checkVCode(@Field("tel") String tel, @Field("smsCode") int VCode);

    @FormUrlEncoded
    @POST("/user/reg")
        /*注册*/
    Observable<MatherResponse> register(@Field("u.accountName") String accountName, @Field("u.pwd") String password,
                                      @Field("u.email") String email, @Field("u.nick") String nickName,
                                      @Field("u.head") String headUrl, @Field("u.sex") String sex,
                                      @Field("u.name") String name, @Field("u.idcard") String iDCard,
                                      @Field("u.phone") String phone, @Field("smsCode") String vCode,
                                      @Field("suffix") String suffix, @Field("base64") String imageBase64,
                                      @Field("gameCoreVersion") String gameCoreVersion);

    @FormUrlEncoded
    @POST("/user/login")
        /*登录*/
    Observable<UserInfoResponse> login(@Field("u") String user, @Field("p") String password,
                                       @Field("gameCoreVersion") int gameCoreVersion);

    @GET("/user/logout")
        /*登出*/
    Observable<MatherResponse> logout();

    @FormUrlEncoded
    @POST("/user/editHeadPic")
        /*用户头像上传修改*/
    Observable<EditHeadPicResponse> editHeadPic(@Field("userid") long userID, @Field("suffix") String suffix,
                                                @Field("base64") String picBase64);

    @FormUrlEncoded
    @POST("/user/editPwd")
        /*用户密码修改*/
    Observable<MatherResponse> editPassword(@Field("tel") String tel, @Field("opwd") String oldPwd,
                                          @Field("type") String type, @Field("pwd") String pwd,
                                          @Field("smsCode") String smsCode);

    @FormUrlEncoded
    @POST("/user/editPayPwd")
    Observable<MatherResponse> editPayPwd(@Field("tel") String phone, @Field("oldPayPwd") String oldPayPwd,
                                      @Field("type") int type/*1用验证码改密码,其他用旧密码*/,
                                      @Field("payPwd") String payPwd, @Field("smsCode") String smsCode);

    @FormUrlEncoded
    @POST("/user/editByPlayer")
        /*用户信息修改*/
    Observable<UserInfoResponse> editByPlayer(@Field("userid") String userId, @Field("u.nick") String userNickName,
                                              @Field("u.head") String userHeader, @Field("suffix") String suffix,
                                              @Field("base64") String picBase64);

    @FormUrlEncoded
    @POST("/user/info")
        /*用户信息*/
    Observable<UserInfo> userInfo(@Field("userid") long userId);


    @FormUrlEncoded
    @POST("/user/findUserByWallet")
    Observable<UserInfoResponse> findUserByWallet(@Field("walletAddr") String walletAddr);

    @FormUrlEncoded
    @POST("/user/exchangeDiamondCoin")
    Observable<MatherResponse> exchangeDiamondCoin(@Field("userid") String userId, @Field("toUserid") String toUserID, @Field("amount") double amount);

    @FormUrlEncoded
    @POST("/user/findPayLog")
    Observable<PayLogResponse> findPayLog(@Field("userid") String userId, @Field("userid1") String userId1,
                                          @Field("typ") int type, @Field("moneyType") int moneyType, @Field("pageNumber") int pageNumber);

    @FormUrlEncoded
    @POST("/user/exchangeDiamondCoin1")
    Observable<MatherResponse> exchangeDiamondCoin1(@Field("walletAddr") String walletAddr, @Field("toWalletAddr") String toWalletAddr, @Field("amount") double amount);

    @FormUrlEncoded
    @POST("/user/findPayLog1")
    Observable<PayLogResponse> findPayLog1(@Field("walletAddr") String walletAddr, @Field("toWalletAddr") String toWalletAddr,
                                           @Field("typ") int type, @Field("moneyType") int moneyType, @Field("pageNumber") int pageNumber);
}
