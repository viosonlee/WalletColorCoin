package com.a7.wallet.views.acitivities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.a7.wallet.R;
import com.a7.wallet.models.MatherResponse;
import com.a7.wallet.network.DataCallBack;
import com.a7.wallet.network.Requester;
import com.a7.wallet.utils.AppDataController;
import com.a7.wallet.utils.AppMD5Util;
import com.a7.wallet.views.fragments.PayPasswordDialog;

import java.lang.ref.WeakReference;
import java.util.Locale;

import lee.vioson.network.core.BaseApiException;

/**
 * Created by viosonlee
 * on 2018/5/30.
 * for 设置或者修改支付密码
 */
public class EditPayPasswordActivity extends BaseActivity implements PayPasswordDialog.PayKeyBoardListener {
    private static final int VCODE_SENT = 0x0001;
    private Button registerBtn;
    private TextView sendVCodeBtn;
    private EditText phone;
    private EditText vCode;
    private TextView password;
    private PayPasswordDialog payPasswordDialog;
    private String pwd;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_edit_pay_pwd;
    }

    @Override
    protected void initView() {
        registerBtn = findViewById(R.id.register_btn);
        sendVCodeBtn = findViewById(R.id.send_sms_code_btn);
        phone = findViewById(R.id.account);
        vCode = findViewById(R.id.sms_code);
        password = findViewById(R.id.password);
    }

    @Override
    protected void initEvent() {
        registerBtn.setOnClickListener(this::commit);
        sendVCodeBtn.setOnClickListener(this::sendVCode);
        password.setOnClickListener(this::inputPassword);
    }

    private void inputPassword(View view) {
        if (payPasswordDialog == null) {
            payPasswordDialog = new PayPasswordDialog();
            payPasswordDialog.setPayKeyBoardListener(this);
        }
        payPasswordDialog.show(getSupportFragmentManager(), true);
    }

    public void back(View view) {
        activity.finish();
    }

    private void commit(View view) {
        String phoneNumber = phone.getText().toString().trim();
        String smsCode = vCode.getText().toString().trim();
//         pwd = password.getText().toString().trim();
        if (checkPhone()) return;
        if (TextUtils.isEmpty(smsCode)) {
            Toast.makeText(this, R.string.error_sms_code_number, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, R.string.error_pwd_number, Toast.LENGTH_SHORT).show();
            return;
        }
        Requester.setPayPassword(phoneNumber, AppMD5Util.getMD5(pwd), smsCode, new DataCallBack<MatherResponse>(this) {
            @Override
            protected void onHandleSuccess(MatherResponse o) {
                Toast.makeText(EditPayPasswordActivity.this, "修改密码成功", Toast.LENGTH_SHORT).show();
                AppDataController.setHasPayPasswordBind(true);
                setResult(RESULT_OK, new Intent().putExtra("pay_password", AppMD5Util.getMD5(pwd)));
                finish();
            }

            @Override
            protected void onHandleError(BaseApiException e) {
                super.onHandleError(e);
                Toast.makeText(EditPayPasswordActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendVCode(View view) {
        if (checkPhone()) return;
        Requester.sendVCode(phone.getText().toString().trim(), 1, new DataCallBack<MatherResponse>(this) {
            @Override
            protected void onHandleSuccess(MatherResponse baseResponse) {
                sendVCodeBtn.setClickable(false);
                sendVCodeBtn.setTextColor(Color.parseColor("#212528"));
                conDownHandler.sendEmptyMessage(VCODE_SENT);
            }

            @Override
            public void onHandleError(BaseApiException e) {
                super.onHandleError(e);
                onDismissLoading();
                Toast.makeText(EditPayPasswordActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ConDownHandler conDownHandler = new ConDownHandler(this);
    private int codeCDTime = 59;

    @Override
    public void onInputPayPwdCompleted(String inputWord) {
        payPasswordDialog.dismiss();
        pwd = inputWord;
        password.setText("******");
    }

    @Override
    public void onPayCancel() {

    }

    @Override
    public void onForgotPayPwd() {

    }

    static class ConDownHandler extends Handler {
        WeakReference<EditPayPasswordActivity> weakReference;

        public ConDownHandler(EditPayPasswordActivity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            EditPayPasswordActivity activity = weakReference.get();
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
}
