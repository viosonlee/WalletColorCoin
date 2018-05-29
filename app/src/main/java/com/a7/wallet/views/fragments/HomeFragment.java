package com.a7.wallet.views.fragments;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.a7.wallet.R;
import com.a7.wallet.models.Coin;
import com.a7.wallet.views.acitivities.ExchangeActivity;
import com.a7.wallet.views.acitivities.PersonQRCodeActivity;
import com.a7.wallet.views.widgets.BaseRecyclerViewAdapter;
import com.a7.wallet.views.widgets.RecyclerViewHolder;
import com.github.shenyuanqing.zxingsimplify.zxing.Activity.CaptureActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by viosonlee
 * on 2018/5/21.
 * for
 */
public class HomeFragment extends Fragment {
    private static final int REQUEST_SCAN = 0x0002;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_new, null);
    }

    private RecyclerView list;
    private List<Coin> data = new ArrayList<>();
    private RxPermissions rxPermissions;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rxPermissions = new RxPermissions(getActivity());
        initView(view);
        initEvent(view);
    }


    private void initView(View view) {
        list = view.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter<Coin>(getActivity(), data) {
            @Override
            protected void bindData(RecyclerViewHolder holder, int position, Coin coin) {
                holder.setText(R.id.short_name, coin.shortName);
                holder.setText(R.id.name, coin.name);
                holder.setText(R.id.number, coin.number + "");
                holder.setText(R.id.value, "￥" + coin.value);
                holder.setText(R.id.present, coin.present + "%");
                holder.getImageView(R.id.icon).setImageResource(coin.iconId);
            }

            @Override
            protected int getItemLayout(int viewType) {
                return R.layout.item_home_list;
            }
        };
        list.setAdapter(adapter);
    }

    private void initEvent(View view) {
        view.findViewById(R.id.scan).setOnClickListener(this::scan);
        view.findViewById(R.id.qr_code).setOnClickListener(this::qrcode);
        view.findViewById(R.id.exchange).setOnClickListener(this::exchange);
    }

    private void exchange(View view) {
        startActivity(new Intent(getActivity(), ExchangeActivity.class));
    }

    private void qrcode(View view) {
        startActivity(new Intent(getActivity(), PersonQRCodeActivity.class));
    }

    private void scan(View view) {
        //扫一扫
        rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) {
                        startActivityForResult(new Intent(getActivity(), CaptureActivity.class), REQUEST_SCAN);
                    } else {
                        Toast.makeText(getActivity(), "无法连接相机", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SCAN && resultCode == RESULT_OK) {
            Toast.makeText(getActivity(), data.getStringExtra("barCode"), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    private void loadData() {
        Coin c0 = new Coin();
        c0.value = 0.00;
        c0.iconId = R.mipmap.icon;
        c0.name = "RainbowCoin";
        c0.shortName = "RPC";
        c0.number = 0.00;
        c0.present = 45;
        data.add(c0);

    }
}
