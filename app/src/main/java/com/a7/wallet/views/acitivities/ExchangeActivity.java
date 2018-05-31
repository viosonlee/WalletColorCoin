package com.a7.wallet.views.acitivities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.a7.wallet.R;
import com.a7.wallet.models.MatherResponse;
import com.a7.wallet.models.QrData;
import com.a7.wallet.network.DataCallBack;
import com.a7.wallet.network.Requester;
import com.a7.wallet.utils.AppDataController;
import com.a7.wallet.views.fragments.PayPasswordDialog;
import com.github.shenyuanqing.zxingsimplify.zxing.Activity.CaptureActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import lee.vioson.network.core.BaseApiException;
import lee.vioson.network.core.BaseObserver;
import lee.vioson.network.core.BaseResponse;
import lee.vioson.network.core.RequestFactory;
import lee.vioson.network.utils.JSONUtils;

/**
 * Created by viosonlee
 * on 2018/5/26.
 * for 转账
 */
public class ExchangeActivity extends BaseActivity implements PayPasswordDialog.PayKeyBoardListener {
    private static final int REQUEST_SCAN = 0x00012;
    private static final int REQUEST_SET_PAY_PASSWORD = 0x00013;
    private static final String QR_DATA = "qr_data";
    private RxPermissions rxPermissions;
    private String address;
    private String amountStr;
    private PayPasswordDialog payPasswordDialog;

    public static void launch(Context context, QrData qrData) {
        Intent intent = new Intent(context, ExchangeActivity.class);
        intent.putExtra(QR_DATA, qrData);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_exchange;
    }

    private EditText inputAddress;
    private EditText inputAmount;
    private TextView amount;


    @Override
    protected void initView() {
        rxPermissions = new RxPermissions(activity);
        inputAddress = findViewById(R.id.input_address);
        inputAmount = findViewById(R.id.input_amount);
        amount = findViewById(R.id.amount);
        amount.setText(AppDataController.getUserInfo().getDiamondCoinAmount());
        QrData qrData = getIntent().getParcelableExtra(QR_DATA);
        if (qrData != null) {
            inputAddress.setText(qrData.address);
            inputAmount.setText(qrData.amount + "");
        }
    }

    @Override
    protected void initEvent() {

    }

    public void back(View view) {
        finish();
    }

    public void exchange(View view) {
        //转账
        address = inputAddress.getText().toString().trim();
        amountStr = inputAmount.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(activity, "地址不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(amountStr)) {
            Toast.makeText(activity, "金额不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (AppDataController.hasPayPwd()) {
            if (payPasswordDialog == null) {
                payPasswordDialog = new PayPasswordDialog();
                payPasswordDialog.setPayKeyBoardListener(this);
            }
            payPasswordDialog.show(getSupportFragmentManager());
        } else {
            Toast.makeText(activity, "请先设置支付密码", Toast.LENGTH_SHORT).show();
            startActivityForResult(new Intent(this, EditPayPasswordActivity.class), REQUEST_SET_PAY_PASSWORD);
        }

    }

    public void scan(View view) {
        rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) {
                        startActivityForResult(new Intent(activity, CaptureActivity.class), REQUEST_SCAN);
                    } else {
                        Toast.makeText(activity, "无法连接相机", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SCAN && resultCode == RESULT_OK) {
//            Toast.makeText(activity, data.getStringExtra("barCode"), Toast.LENGTH_LONG).show();
//            inputAddress.setText(data.getStringExtra("barCode"));
            String barCode = data.getStringExtra("barCode");
            if (!TextUtils.isEmpty(barCode)) {
                try {
                    QrData qrData = JSONUtils.fromJson(barCode, QrData.class);
                    inputAddress.setText(qrData.address);
                    inputAmount.setText(qrData.amount + "");
                } catch (Exception e) {
                    Toast.makeText(activity, "二维码错误", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (requestCode == REQUEST_SET_PAY_PASSWORD && resultCode == RESULT_OK) {
            exchange(null);
        }
    }

    @Override
    public void onInputPayPwdCompleted(String inputWord) {
        payPasswordDialog.dismiss();
        Requester.exchangeDiamondCoinByAddress(AppDataController.getUserInfo().getWalletAddr(), address,
                Double.parseDouble(amountStr), new DataCallBack<MatherResponse>() {
                    @Override
                    protected void onHandleSuccess(MatherResponse data) {
                        Toast.makeText(activity, "转账成功", Toast.LENGTH_SHORT).show();
                        activity.finish();
                    }

                    @Override
                    protected void onHandleError(BaseApiException e) {
                        super.onHandleError(e);
                        Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onPayCancel() {
        Toast.makeText(activity, "支付取消", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onForgotPayPwd() {
        startActivityForResult(EditPayPasswordActivity.class,REQUEST_SET_PAY_PASSWORD);
    }
}
