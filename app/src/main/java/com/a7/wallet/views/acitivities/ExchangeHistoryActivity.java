package com.a7.wallet.views.acitivities;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a7.wallet.R;
import com.a7.wallet.models.PayLogResponse;
import com.a7.wallet.network.Requester;
import com.a7.wallet.utils.AppDataController;
import com.a7.wallet.utils.BitmapUtil;
import com.a7.wallet.utils.DateUtil;
import com.a7.wallet.views.widgets.BaseRecyclerViewAdapter;
import com.a7.wallet.views.widgets.RecycleViewDivider;
import com.a7.wallet.views.widgets.RecyclerViewHolder;
import com.a7.wallet.views.widgets.UpLoadListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lee.vioson.network.core.BaseObserver;

/**
 * Created by viosonlee
 * on 2018/5/28.
 * for
 */
public class ExchangeHistoryActivity extends BaseActivity {
    private BaseRecyclerViewAdapter<PayLogResponse.PageQueryBean.ListBean> adapter;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_exchange_history;
    }

    private RecyclerView list;
    private List<PayLogResponse.PageQueryBean.ListBean> data = new ArrayList<>();

    @Override
    protected void initView() {
        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(activity));
        list.addItemDecoration(new RecycleViewDivider(activity, LinearLayout.HORIZONTAL, 1,
                activity.getResources().getColor(R.color.lineColor)));
        adapter = new BaseRecyclerViewAdapter<PayLogResponse.PageQueryBean.ListBean>(activity, data) {
            @Override
            protected void bindData(RecyclerViewHolder holder, int position, PayLogResponse.PageQueryBean.ListBean listBean) {
                String walletAddr1 = listBean.getWalletAddr1();
                holder.setText(R.id.address, walletAddr1);
                holder.setText(R.id.time, DateUtil.getMinute(listBean.getCreateTime()));
                TextView amount = holder.getTextView(R.id.amount);
                double num = listBean.getNum();
                if (num > 0) {
                    amount.setTextColor(Color.parseColor("#00cda2"));
                    amount.setText("+"+String.format(num + ""));
                } else {
                    amount.setTextColor(Color.parseColor("#e2525e"));
                    amount.setText(String.valueOf(num));
                }
                String avatarStr = walletAddr1.substring(walletAddr1.length() - 4);
                holder.getImageView(R.id.avatar).setImageBitmap(BitmapUtil.strCreateBitmap(avatarStr));
            }

            @Override
            protected int getItemLayout(int viewType) {
                return R.layout.item_exchange_list;
            }

            @Override
            protected OnItemClickListener getOnItemClickListener() {
                return (view, position) -> ExchangeDetailActivity.launch(activity, data.get(position));
            }
        };
        list.setAdapter(adapter);
        list.addOnScrollListener(new UpLoadListener(list.getLayoutManager()) {
            @Override
            protected void onLoadMore() {
                if (hasMore()) {
                    page++;
                    loadData();
                }

            }
        });
        loadData();
    }

    private boolean hasMore() {
        return null != payLogResponse && !payLogResponse.getPageQuery().isLastPage();
    }

    private int page = 1;
    private PayLogResponse payLogResponse = null;

    private void loadData() {
        Requester.findPayLog(AppDataController.getUserInfo().getId() + "", "",
                1, 3, page, new BaseObserver<PayLogResponse>(this) {
                    @Override
                    protected void onHandleSuccess(PayLogResponse response) {
                        payLogResponse = response;
                        if (page == 1) {
                            data.clear();
                            data.addAll(response.getPageQuery().getList());
                            adapter.notifyDataSetChanged();
                        } else {
                            int oldDataSize = data.size();
                            data.addAll(response.getPageQuery().getList());
                            if (oldDataSize > 0)
                                adapter.notifyItemInserted(oldDataSize - 1);
                            else adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    public void back(View view) {
        activity.finish();
    }
}
