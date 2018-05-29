package com.a7.wallet.views.acitivities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.a7.wallet.R;
import com.a7.wallet.models.LoginInfo;
import com.a7.wallet.network.Requester;
import com.a7.wallet.utils.AppDataController;

import java.lang.ref.WeakReference;
import java.util.Locale;

import lee.vioson.network.core.BaseObserver;
import lee.vioson.network.core.BaseResponse;

/**
 * Created by viosonlee
 * on 2018/5/23.
 * for
 */
public class ForgotPwdActivity extends BaseActivity {
    private static final int VCODE_SENT = 0x0001;
    private Button registerBtn;
    private TextView sendVCodeBtn;
    private EditText phone;
    private EditText vCode;
    private EditText password;


    @Override
    protected int getContentLayout() {
        return R.layout.activity_forgot_pwd;
    }

    protected void initView() {
        registerBtn = findViewById(R.id.register_btn);
        sendVCodeBtn = findViewById(R.id.send_sms_code_btn);
        phone = findViewById(R.id.account);
        vCode = findViewById(R.id.sms_code);
        password = findViewById(R.id.password);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        registerBtn.setOnClickListener(this::commit);
        sendVCodeBtn.setOnClickListener(this::sendVCode);
    }


    private void sendVCode(View view) {
        if (checkPhone()) return;
        Requester.sendVCode(phone.getText().toString().trim(), 1, new BaseObserver(this) {
            @Override
            protected void onHandleSuccess(BaseResponse baseResponse) {
                sendVCodeBtn.setClickable(false);
                sendVCodeBtn.setTextColor(Color.parseColor("#212528"));
                conDownHandler.sendEmptyMessage(VCODE_SENT);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                onDismissLoading();
                Toast.makeText(ForgotPwdActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ConDownHandler conDownHandler = new ConDownHandler(this);
    private int codeCDTime = 59;

    static class ConDownHandler extends Handler {
        WeakReference<ForgotPwdActivity> weakReference;

        public ConDownHandler(ForgotPwdActivity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ForgotPwdActivity activity = weakReference.get();
            activity.sendVCodeBtn
                    .setText(String.format(Locale.CHINA, "已发送（%d）",
                            activity.codeCDTime));
            activity.codeCDTime--;
            if (activity.codeCDTime > 0) {
                activity.conDownHandler.sendEmptyMessageDelayed(VCODE_SENT, 1000);
            } else {
                activity.resendCode();
            }
        }
    }

    //进入重新发送验证码状态
    private void resendCode() {
        codeCDTime = 59;
        sendVCodeBtn.setClickable(true);
        sendVCodeBtn.setText("重新发送");
        sendVCodeBtn.setTextColor(Color.parseColor("#8c78eb"));
    }

    public boolean checkPhone() {
        boolean empty = TextUtils.isEmpty(phone.getText().toString().trim());
        if (empty) {
            Toast.makeText(this, R.string.error_empty_phone_number, Toast.LENGTH_SHORT).show();
        }
        return empty;
    }

    private void commit(View view) {
        String phoneNumber = phone.getText().toString().trim();
        String smsCode = vCode.getText().toString().trim();
        String pwd = password.getText().toString().trim();
        if (checkPhone()) return;
        if (TextUtils.isEmpty(smsCode)) {
            Toast.makeText(this, R.string.error_sms_code_number, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, R.string.error_pwd_number, Toast.LENGTH_SHORT).show();
            return;
        }
        Requester.editPassword(phoneNumber, "", "1", pwd, smsCode, new BaseObserver(this) {
            @Override
            protected void onHandleSuccess(BaseResponse o) {
                Toast.makeText(ForgotPwdActivity.this, "修改密码成功", Toast.LENGTH_SHORT).show();
                LoginInfo loginInfo = new LoginInfo(phoneNumber, pwd);
                AppDataController.saveLoginInfo(loginInfo);
                startActivity(LoginActivity.class);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Toast.makeText(ForgotPwdActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
