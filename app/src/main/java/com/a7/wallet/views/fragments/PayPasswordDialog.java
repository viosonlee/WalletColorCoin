package com.a7.wallet.views.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.a7.wallet.R;

import java.util.Locale;

/**
 * Created by viosonlee
 * on 2018/5/30.
 * for
 */
public class PayPasswordDialog extends DialogFragment {
    private static final String TAG = "pay_dialog";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.Theme_AppCompat);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().getAttributes().windowAnimations = R.style.PayKeyboardTheme;
        return inflater.inflate(R.layout.dialog_pay_password, null);
    }

    private TextView tips;
    private Integer[] chars = new Integer[6];
    private int position = 0;
    private CheckBox[] pwdTvs = new CheckBox[6];

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tips = view.findViewById(R.id.tips);
        pwdTvs[0] = view.findViewById(R.id.p0);
        pwdTvs[1] = view.findViewById(R.id.p1);
        pwdTvs[2] = view.findViewById(R.id.p2);
        pwdTvs[3] = view.findViewById(R.id.p3);
        pwdTvs[4] = view.findViewById(R.id.p4);
        pwdTvs[5] = view.findViewById(R.id.p5);


        view.findViewById(R.id.cancel_btn).setOnClickListener(v -> {
            if (null != payKeyBoardListener) {
                payKeyBoardListener.onPayCancel();
            }
            PayPasswordDialog.this.dismiss();
        });
        TextView forgotPwd = view.findViewById(R.id.forgot_pwd);
        forgotPwd.setVisibility(dismissForgotPwd ? View.GONE : View.VISIBLE);
        forgotPwd.setOnClickListener(this::forgotPayPwd);
        view.findViewById(R.id.num_0).setOnClickListener(this::input);
        view.findViewById(R.id.num_1).setOnClickListener(this::input);
        view.findViewById(R.id.num_2).setOnClickListener(this::input);
        view.findViewById(R.id.num_3).setOnClickListener(this::input);
        view.findViewById(R.id.num_4).setOnClickListener(this::input);
        view.findViewById(R.id.num_5).setOnClickListener(this::input);
        view.findViewById(R.id.num_6).setOnClickListener(this::input);
        view.findViewById(R.id.num_7).setOnClickListener(this::input);
        view.findViewById(R.id.num_8).setOnClickListener(this::input);
        view.findViewById(R.id.num_9).setOnClickListener(this::input);
        view.findViewById(R.id.delete).setOnClickListener(this::input);
    }

    private void input(View view) {
        switch (view.getId()) {
            case R.id.num_0:
                inputNumber(0);
                break;
            case R.id.num_1:
                inputNumber(1);
                break;
            case R.id.num_2:
                inputNumber(2);
                break;
            case R.id.num_3:
                inputNumber(3);
                break;
            case R.id.num_4:
                inputNumber(4);
                break;
            case R.id.num_5:
                inputNumber(5);
                break;
            case R.id.num_6:
                inputNumber(6);
                break;
            case R.id.num_7:
                inputNumber(7);
                break;
            case R.id.num_8:
                inputNumber(8);
                break;
            case R.id.num_9:
                inputNumber(9);
                break;
            case R.id.delete:
                if (position > 0) {
                    position--;
                    pwdTvs[position].setChecked(false);
                    chars[position] = null;
                }
                break;

        }
    }

    public void setTips(String str) {
        tips.setText(str);
    }


    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, TAG);
    }

    private boolean dismissForgotPwd;

    public void show(FragmentManager fragmentManager, boolean dismissForgotPwd) {
        show(fragmentManager, TAG);
        this.dismissForgotPwd = dismissForgotPwd;
    }

    private void inputNumber(int number) {
        if (position > 5) return;
        chars[position] = number;
        pwdTvs[position].setChecked(true);
        if (null != payKeyBoardListener && position == 5) {
            payKeyBoardListener.onInputPayPwdCompleted(String.format(Locale.CHINA, "%d%d%d%d%d%d",
                    chars[0], chars[1], chars[2], chars[3], chars[4], chars[5]));
        }
        position++;

    }

    private void forgotPayPwd(View view) {
        //忘记支付密码
        if (null != payKeyBoardListener)
            payKeyBoardListener.onForgotPayPwd();
    }

    private PayKeyBoardListener payKeyBoardListener;

    public void setPayKeyBoardListener(PayKeyBoardListener payKeyBoardListener) {
        this.payKeyBoardListener = payKeyBoardListener;
    }

    public interface PayKeyBoardListener {
        void onInputPayPwdCompleted(String inputWord);

        void onPayCancel();

        void onForgotPayPwd();
    }
}
