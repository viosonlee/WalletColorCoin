package com.a7.wallet.views.acitivities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.a7.wallet.R;
import com.a7.wallet.views.fragments.HomeFragment;
import com.a7.wallet.views.fragments.MarketFragment;
import com.a7.wallet.views.fragments.MemberFragment;
import com.a7.wallet.views.fragments.NewsFragment;

/**
 * Created by viosonlee
 * on 2018/5/18.
 * for
 */
public class MainActivity extends BaseActivity {
    private ViewPager mVp;
    private Fragment[] fragments;
    private RadioButton[] radioButtons;
    private TextView title;

    @Override
    protected void initView() {
        super.initView();
        title = findViewById(R.id.title);
        mVp = findViewById(R.id.view_pager);
        radioButtons = new RadioButton[4];
        radioButtons[0] = findViewById(R.id.home_btn);
        radioButtons[1] = findViewById(R.id.market_btn);
        radioButtons[2] = findViewById(R.id.news_btn);
        radioButtons[3] = findViewById(R.id.user_btn);
        for (int i = 0; i < radioButtons.length; i++) {
            final int index = i;
            radioButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mVp.setCurrentItem(index);
                }
            });
        }
        HomeFragment homeFragment = HomeFragment.newInstance();
        MarketFragment marketFragment = MarketFragment.newInstance();
        NewsFragment newsFragment = NewsFragment.newInstance();
        MemberFragment memberFragment = MemberFragment.newInstance();
        fragments = new Fragment[4];
        fragments[0] = homeFragment;
        fragments[1] = marketFragment;
        fragments[2] = newsFragment;
        fragments[3] = memberFragment;
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
                switch (position) {
                    case 0:
                        title.setText("总资产");
                        break;
                    case 1:
                        title.setText("行情");
                        break;
                    case 2:
                        title.setText("资讯");
                        break;
                    case 3:
                        title.setText("");
                        break;
                }
            }
        });
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main_new;
    }
}
