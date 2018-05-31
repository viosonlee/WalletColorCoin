package com.a7.wallet.network;

import com.a7.wallet.App;
import com.a7.wallet.models.MatherResponse;
import com.a7.wallet.views.acitivities.LoginActivity;

import lee.vioson.network.core.BaseApiException;
import lee.vioson.network.core.BaseObserver;
import lee.vioson.network.core.BaseResponse;
import lee.vioson.network.core.LoadingListener;

/**
 * Created by viosonlee
 * on 2018/5/30.
 * for
 */
public abstract class DataCallBack<T extends MatherResponse> extends BaseObserver<T> {
    public DataCallBack(LoadingListener loadingListener) {
        super(loadingListener);
    }

    public DataCallBack() {
    }

    @Override
    protected void onHandleError(BaseApiException e) {
        super.onHandleError(e);
        if (e.getErrorCode() == -900) {
            LoginActivity.reLogin(App.APP_CONTEXT);
        }
    }
}
