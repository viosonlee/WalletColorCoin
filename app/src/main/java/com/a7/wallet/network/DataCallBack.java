package com.a7.wallet.network;

import lee.vioson.network.core.BaseObserver;
import lee.vioson.network.core.BaseResponse;

/**
 * Created by viosonlee
 * on 2018/5/23.
 * for
 */
public abstract class DataCallBack<T> extends BaseObserver<T> {
    public DataCallBack() {
    }

    public DataCallBack(LoadingListener loadingListener) {
        super(loadingListener);
    }

    @Override
    public boolean responseIsOk(BaseResponse<T> response) {
        return response.getCode() > 0;
    }

}
