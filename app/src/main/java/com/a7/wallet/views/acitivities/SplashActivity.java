package com.a7.wallet.views.acitivities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.a7.wallet.common.BaseActivityImp;

/**
 * Created by viosonlee
 * on 2018/5/21.
 * for
 */
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this,LoginActivity.class));
            SplashActivity.this.finish();
        }, 1000);
    }
}
