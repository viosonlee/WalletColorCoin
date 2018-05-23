package com.a7.wallet.models;

/**
 * Created by viosonlee
 * on 2018/5/23.
 * for
 */
public class UserInfoSimple {

    /**
     * head : /upload/default.png
     * id : 123
     * nick : e123
     */

    private String head;
    private int id;
    private String nick;

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

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
