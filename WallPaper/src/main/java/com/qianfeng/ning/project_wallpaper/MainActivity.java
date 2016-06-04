package com.qianfeng.ning.project_wallpaper;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.qianfeng.ning.project_wallpaper.fragment.FragmentFactory;



public class MainActivity extends AppCompatActivity {
    @Bind(R.id.framlayout)
    FrameLayout framlayout;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    private FragmentManager manger;
    private FragmentTransaction transaction;
    private Fragment fragment;
    private int selectedId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        manger = getSupportFragmentManager();
        initTabs();
        initFragments(selectedId);
    }

    private void initTabs() {
        tablayout.setSelectedTabIndicatorHeight(0);
        tablayout.setTabTextColors(Color.DKGRAY, Color.rgb(22, 223, 104));
        TabLayout.Tab tab = tablayout.newTab().setText("推荐").setIcon(R.drawable.recommand).setTag(1);
        tablayout.addTab(tab);
        TabLayout.Tab tab1 = tablayout.newTab().setText("分类").setIcon(R.drawable.category).setTag(2);
        tablayout.addTab(tab1);
        TabLayout.Tab tab2 = tablayout.newTab().setText("搜索").setIcon(R.drawable.search).setTag(3);
        tablayout.addTab(tab2);
        TabLayout.Tab tab3 = tablayout.newTab().setText("更多").setIcon(R.drawable.more).setTag(4);
        tablayout.addTab(tab3);

        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectedId = (int) tab.getTag();
                initFragments(selectedId);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initFragments(int selectedId) {

        transaction = manger.beginTransaction();
        fragment = FragmentFactory.getInstanceByIndex(selectedId);
        transaction.replace(R.id.framlayout, fragment);
        transaction.commit();
    }
}



