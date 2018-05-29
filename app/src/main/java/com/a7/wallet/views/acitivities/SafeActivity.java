package com.a7.wallet.views.acitivities;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.a7.wallet.R;
import com.a7.wallet.utils.AppDataController;
import com.leo.gesturelibray.enums.LockMode;
import com.leo.gesturelibray.view.CustomLockView;

/**
 * Created by viosonlee
 * on 2018/5/28.
 * for
 */
public class SafeActivity extends BaseActivity implements CustomLockView.OnCompleteListener {
    private static final String PIN_KEY = "pin_key";
    private static final String LOCK_MODE = "lock_mode";
    private CustomLockView lockView;
    private TextView tip;
    private LockMode lockMode;
    private TextView title;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_safe;
    }

    public static void launch(Context context, LockMode lockMode) {
        Intent intent = new Intent(context, SafeActivity.class);
        intent.putExtra(LOCK_MODE, lockMode);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        tip = findViewById(R.id.tip);
        title = findViewById(R.id.title);
        lockView = findViewById(R.id.lock_view);
        lockView.setShow(true);
        lockView.setErrorNumber(5);
        lockView.setPasswordMinLength(4);
        lockView.setSavePin(true);
        lockView.setSaveLockKey(PIN_KEY);
        lockMode = (LockMode) getIntent().getSerializableExtra(LOCK_MODE);
        if (null != lockMode) {
            lockView.setMode(lockMode);
            switch (lockMode) {
                case EDIT_PASSWORD:
                    title.setText("修改密码");
                    break;
                case CLEAR_PASSWORD:
                    title.setText("清除密码");
                    break;
                case VERIFY_PASSWORD:
                    title.setText("验证密码");
                    lockView.setOldPassword(AppDataController.getSafePassword());
                    break;
                case SETTING_PASSWORD:
                    title.setText("设置密码");
                    break;
            }
        }
    }

    @Override
    protected void initEvent() {
        lockView.setOnCompleteListener(this);
    }

    @Override
    public void onComplete(String password, int[] indexs) {
        if (lockMode == LockMode.VERIFY_PASSWORD) {
            startActivity(MainActivity.class);
        } else if (lockMode == LockMode.SETTING_PASSWORD || lockMode == LockMode.EDIT_PASSWORD) {
            AppDataController.saveSafePassWord(password);
            SafeActivity.launch(this, LockMode.VERIFY_PASSWORD);
            lockView.setOldPassword(password);
        }
        finish();
    }

    @Override
    public void onError(String errorTimes) {
        tip.setText("密码错误");
    }

    @Override
    public void onPasswordIsShort(int passwordMinLength) {
        tip.setText("最少连接4个点");
    }

    @Override
    public void onAginInputPassword(LockMode mode, String password, int[] indexs) {
        tip.setText("请再次输入密码");
    }

    @Override
    public void onInputNewPassword() {
        tip.setText("请输入新密码");
    }

    @Override
    public void onEnteredPasswordsDiffer() {
        tip.setText("两次输入的密码不一致");
    }

    @Override
    public void onErrorNumberMany() {
        tip.setText("密码错误次数超过限制，不能再输入");
    }
}
