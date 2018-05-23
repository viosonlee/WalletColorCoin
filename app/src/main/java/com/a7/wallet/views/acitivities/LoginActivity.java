package com.a7.wallet.views.acitivities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.a7.wallet.R;
import com.a7.wallet.models.LoginInfo;
import com.a7.wallet.models.UserInfo;
import com.a7.wallet.network.DataCallBack;
import com.a7.wallet.network.Requester;
import com.a7.wallet.utils.AppDataController;

import lee.vioson.network.core.BaseObserver;
import lee.vioson.network.core.BaseResponse;

/**
 * Created by viosonlee
 * on 2018/5/23.
 * for
 */
public class LoginActivity extends BaseActivity {

    private EditText phone;
    private EditText password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        bindEvent();
        initData();
    }

    private void initView() {
        phone = findViewById(R.id.account);
        password = findViewById(R.id.password);
    }

    private void bindEvent() {
        findViewById(R.id.login_btn).setOnClickListener(this::login);
        findViewById(R.id.forgot_pwd).setOnClickListener(this::forgotPwd);
        findViewById(R.id.to_register).setOnClickListener(this::toRegister);

    }

    private void initData() {
        LoginInfo loginInfo = AppDataController.getLoginInfo();
        if (!TextUtils.isEmpty(loginInfo.phone)) {
            phone.setText(loginInfo.phone);
        }
        if (!TextUtils.isEmpty(loginInfo.password)) {
            password.setText(loginInfo.password);
        }
    }

    private void toRegister(View view) {
        startActivity(RegisterActivity.class);
    }

    private void forgotPwd(View view) {
        startActivity(ForgotPwdActivity.class);
    }

    private void login(View view) {
        String phoneNumber = phone.getText().toString().trim();
        String pwd = password.getText().toString().trim();
        if (checkPhone()) return;
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, R.string.error_pwd_number, Toast.LENGTH_SHORT).show();
            return;
        }
        Requester.login(phoneNumber, pwd, 0, new DataCallBack<UserInfo>(this) {
            @Override
            protected void onHandleSuccess(UserInfo userInfo) {
                startActivity(MainActivity.class);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean checkPhone() {
        boolean empty = TextUtils.isEmpty(phone.getText().toString().trim());
        if (empty) {
            Toast.makeText(this, R.string.error_empty_phone_number, Toast.LENGTH_SHORT).show();
        }
        return empty;
    }
}
