package com.a7.wallet.views.acitivities;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.a7.wallet.App;
import com.a7.wallet.R;
import com.a7.wallet.models.LoginInfo;
import com.a7.wallet.models.UserInfoResponse;
import com.a7.wallet.network.Requester;
import com.a7.wallet.utils.AppDataController;
import com.leo.gesturelibray.enums.LockMode;

import lee.vioson.network.core.BaseApiException;
import lee.vioson.network.core.BaseObserver;

/**
 * Created by viosonlee
 * on 2018/5/23.
 * for
 */
public class LoginActivity extends BaseActivity {

    private EditText phone;
    private EditText password;


    @Override
    protected int getContentLayout() {
        return R.layout.activity_login;
    }

    protected void initView() {
        phone = findViewById(R.id.account);
        password = findViewById(R.id.password);
        if (AppDataController.isLogin()) {
            if (!TextUtils.isEmpty(AppDataController.getSafePassword())) {//去设置图案密码
                SafeActivity.launch(activity, LockMode.VERIFY_PASSWORD);
            } else {
                startActivity(MainActivity.class);
            }
            activity.finish();
        }
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        findViewById(R.id.login_btn).setOnClickListener(this::login);
        findViewById(R.id.forgot_pwd).setOnClickListener(this::forgotPwd);
        findViewById(R.id.to_register).setOnClickListener(this::toRegister);
    }


    protected void loadLoginInfo() {
        LoginInfo loginInfo = AppDataController.getLoginInfo();
        if (!TextUtils.isEmpty(loginInfo.phone)) {
            phone.setText(loginInfo.phone);
        }
        if (!TextUtils.isEmpty(loginInfo.password)) {
            password.setText(loginInfo.password);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLoginInfo();
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
        Requester.login(phoneNumber, pwd, 1, new BaseObserver<UserInfoResponse>(this) {
            @Override
            protected void onHandleSuccess(UserInfoResponse userInfo) {
                AppDataController.saveUserInfo(userInfo.user);
                AppDataController.saveLoginInfo(new LoginInfo(phoneNumber, pwd));
                if (TextUtils.isEmpty(AppDataController.getSafePassword())) {//去设置图案密码
                    SafeActivity.launch(activity, LockMode.SETTING_PASSWORD);
                    Toast.makeText(activity, "为保障安全，请设置手势密码", Toast.LENGTH_LONG).show();
                } else {
                    SafeActivity.launch(activity, LockMode.VERIFY_PASSWORD);
                    activity.finish();
                }
            }

            @Override
            protected void onHandleError(BaseApiException e) {
                super.onHandleError(e);
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
