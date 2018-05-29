package com.a7.wallet.views.acitivities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.a7.wallet.R;
import com.a7.wallet.models.QrData;
import com.a7.wallet.models.UserInfo;
import com.a7.wallet.utils.AppDataController;
import com.a7.wallet.utils.QRCodeUtil;

import lee.vioson.network.utils.JSONUtils;

/**
 * Created by viosonlee
 * on 2018/5/26.
 * for 个人收款的二维码
 */
public class PersonQRCodeActivity extends BaseActivity {
    private ImageView avatar;
    private TextView address;
    private ImageView qrCode;
    private EditText amount;


    @Override
    protected int getContentLayout() {
        return R.layout.activity_person_qrcode;
    }

    @Override
    protected void initView() {
        super.initView();
        avatar = findViewById(R.id.avatar);
        address = findViewById(R.id.address);
        qrCode = findViewById(R.id.qr_code_iv);
        amount = findViewById(R.id.amount);
    }

    @Override
    protected void initEvent() {
        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                qrCode.setImageBitmap(QRCodeUtil.createQRCode(getQrString(), 1000, 1000));
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        UserInfo userInfo = AppDataController.getUserInfo();
        address.setText(userInfo.getWalletAddr());
        qrCode.setImageBitmap(QRCodeUtil.createQRCode(getQrString(), 1000, 1000));
    }

    public void back(View view) {
        finish();
    }

    private String getQrString() {
        QrData qrData = new QrData();
        String amountStr = amount.getText().toString().trim();
        qrData.address = AppDataController.getUserInfo().getWalletAddr();
        qrData.amount = TextUtils.isEmpty(amountStr) ? 0 : Double.parseDouble(amountStr);
        return JSONUtils.toJson(qrData);
    }

    public void copyAddress(View view) {
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData mClipData = ClipData.newPlainText("Label", AppDataController.getUserInfo().getWalletAddr());
        if (cm != null) {
            cm.setPrimaryClip(mClipData);
            Toast.makeText(this, "地址已复制", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "地址复制出错", Toast.LENGTH_SHORT).show();
        }
    }
}
