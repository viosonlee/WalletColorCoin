package com.a7.wallet.views.acitivities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.a7.wallet.R;
import com.a7.wallet.views.fragments.HomeFragmentOld;
import com.a7.wallet.views.fragments.TradeFragment;
import com.a7.wallet.views.fragments.UserFragment;

public class MainActivityOld extends AppCompatActivity {
    private ViewPager mVp;
    private Fragment[] fragments;
    private RadioButton[] radioButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVp = findViewById(R.id.view_pager);
        radioButtons = new RadioButton[3];
        radioButtons[0] = findViewById(R.id.home_btn);
        radioButtons[1] = findViewById(R.id.trade_btn);
        radioButtons[2] = findViewById(R.id.user_btn);
        for (int i = 0; i < radioButtons.length; i++) {
            final int index = i;
            radioButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mVp.setCurrentItem(index);
                }
            });
        }
        HomeFragmentOld homeFragment = HomeFragmentOld.newInstance();
        TradeFragment tradeFragment = TradeFragment.newInstance();
        UserFragment userFragment = UserFragment.newInstance();
        fragments = new Fragment[3];
        fragments[0] = homeFragment;
        fragments[1] = tradeFragment;
        fragments[2] = userFragment;
        mVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });
        mVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                radioButtons[position].setChecked(true);
            }
        });
    }
}
