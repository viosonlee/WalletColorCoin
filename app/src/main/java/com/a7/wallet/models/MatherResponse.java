package com.a7.wallet.models;

import lee.vioson.network.core.BaseResponse;

/**
 * Created by viosonlee
 * on 2018/5/30.
 * for
 */
public class MatherResponse<T> extends BaseResponse<T> {
    @Override
    public boolean isOk() {
        return getCode() > 0;
    }
}
