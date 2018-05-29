package com.a7.wallet.views.acitivities;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.a7.wallet.R;
import com.a7.wallet.models.PayLogResponse;
import com.a7.wallet.utils.DateUtil;

/**
 * Created by viosonlee
 * on 2018/5/28.
 * for
 */
public class ExchangeDetailActivity extends BaseActivity {
    private static final String DATA = "ExchangeDetailActivity.data";

    public static void launch(Context context, PayLogResponse.PageQueryBean.ListBean data) {
        Intent intent = new Intent(context, ExchangeDetailActivity.class);
        intent.putExtra(DATA, data);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_exchange_detail;
    }

    public void back(View view) {
        activity.finish();
    }

    private TextView content;
    private TextView amount;
    private TextView address;
    private TextView mark;
    private TextView time;

    @Override
    protected void initView() {
        super.initView();
        PayLogResponse.PageQueryBean.ListBean data = getIntent().getParcelableExtra(DATA);
        content = findViewById(R.id.content);
        address = findViewById(R.id.address);
        amount = findViewById(R.id.amount);
        mark = findViewById(R.id.mark);
        time = findViewById(R.id.time);
        if (null != data) {
//            content.setText(data.getContent());
            address.setText(data.getWalletAddr1());
            amount.setText(data.getNum() + "");
            mark.setText(data.getContent());
            time.setText(DateUtil.getMinute(data.getCreateTime()));
        }
    }
}
