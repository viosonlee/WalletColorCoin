package com.a7.wallet.models;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.math.BigInteger;

import lee.vioson.network.core.BaseResponse;

/**
 * Created by viosonlee
 * on 2018/5/23.
 * for
 */
public class UserInfo extends BaseResponse {

    /**
     * accountName : a
     * createTime : 1526957968000
     * diamondCoin : 0
     * email : a@a
     * head : /upload/default.png
     * id : 1
     * idcard : 210210200101011000
     * name : a
     * nick : a
     * phone : a
     * pwdEncrypted : 1
     * sex : 男
     * status : 1
     * token : 9980a97ff2b5413a87fd433ec93af08e
     * walletAddr : 16945d69f6e9448d8976cc1fabf236d1af60c818d6f44b7484da99b3cf6caadb
     */

    @Expose
    private String accountName;
    @Expose
    private long createTime;
    @Expose
    private BigDecimal diamondCoin;
    @Expose
    private String email;
    @Expose
    private String head;
    @Expose
    private int id;
    @Expose
    private String idcard;
    @Expose
    private String name;
    @Expose
    private String nick;
    @Expose
    private String phone;
    @Expose
    private int pwdEncrypted;
    @Expose
    private String sex;
    @Expose
    private int status;
    @Expose
    private String token;
    @Expose
    private String walletAddr;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getDiamondCoin() {
        return diamondCoin;
    }

    public void setDiamondCoin(BigDecimal diamondCoin) {
        this.diamondCoin = diamondCoin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPwdEncrypted() {
        return pwdEncrypted;
    }

    public void setPwdEncrypted(int pwdEncrypted) {
        this.pwdEncrypted = pwdEncrypted;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getWalletAddr() {
        return walletAddr;
    }

    public void setWalletAddr(String walletAddr) {
        this.walletAddr = walletAddr;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "accountName='" + accountName + '\'' +
                ", createTime=" + createTime +
                ", diamondCoin=" + diamondCoin +
                ", email='" + email + '\'' +
                ", head='" + head + '\'' +
                ", id=" + id +
                ", idcard='" + idcard + '\'' +
                ", name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                ", phone='" + phone + '\'' +
                ", pwdEncrypted=" + pwdEncrypted +
                ", sex='" + sex + '\'' +
                ", status=" + status +
                ", token='" + token + '\'' +
                ", walletAddr='" + walletAddr + '\'' +
                '}';
    }
}
