package com.a7.wallet.views.acitivities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.a7.wallet.common.BaseActivityImp;
import com.a7.wallet.utils.DialogHelper;

import lee.vioson.network.core.BaseObserver;
import lee.vioson.network.core.LoadingListener;

/**
 * Created by viosonlee
 * on 2018/5/23.
 * for
 */
public abstract class BaseActivity extends AppCompatActivity implements LoadingListener {
    protected Dialog loadingDialog;
    protected BaseActivity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = DialogHelper.getProgressDialog(this);
        activity = this;
        setContentView(getContentLayout());
        initView();
        initEvent();
        initData();
    }

    protected abstract int getContentLayout();


    protected void initView() {
    }


    protected void initEvent() {
    }


    protected void initData() {
    }


    public void onStartLoading() {
        loadingDialog.show();
    }

    public void onDismissLoading() {
        loadingDialog.dismiss();
    }

    public void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    public void startActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(BaseActivityImp.BUNDLE, bundle);
        startActivity(intent);
    }

}
