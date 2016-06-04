package com.qianfeng.ning.project_wallpaper.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.qianfeng.ning.project_wallpaper.R;
import com.qianfeng.ning.project_wallpaper.adapter.Search_PagerAdapter;
import com.qianfeng.ning.project_wallpaper.bean.MoreBean;

import java.util.ArrayList;

/**
 * Created by Ning on 2016/6/3.
 */
public class Item_more_adv extends LinearLayout {

    private ViewPager pager;
    private RadioGroup rg;
    private Search_PagerAdapter adapter;
    private Context context;
    private Runnable r;
    private Handler handler = new Handler();
    private boolean flag = false;
    private ImageLoader loader;
    private ArrayList<MoreBean.DataBean.TopicBean> moreList;
    //用于控制ViewPager中显示的数据源
    private ArrayList<View> pagerList = new ArrayList<>();

    public Item_more_adv(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Item_more_adv(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        //把解析好的View对象添加到当前控件里进行显示
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_more, this, true);
        initView(view);
        //设置适配器
        initAdapter();
        //设置监听事件
        setLintener();

    }

    private void startSelect() {
        r = new Runnable() {

            @Override
            public void run() {
                int position = pager.getCurrentItem() + 1;
                pager.setCurrentItem(position);
                handler.postDelayed(r, 2000);
            }
        };
        handler.postDelayed(r, 2000);
    }

    private void setLintener() {
//        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                RadioButton rb = (RadioButton) rg.findViewById(checkedId);
//                int position = rb.getId();
//                pager.setCurrentItem(position % 5);
//            }
//        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rg.check(position % 5);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                switch (state) {
//                    case ViewPager.SCROLL_STATE_DRAGGING:
//                        flag = true;
//                        handler.removeCallbacks(r);
//                        break;
//                    case ViewPager.SCROLL_STATE_IDLE:
//                        if (flag) {
//                            flag = false;
//                            handler.postDelayed(r, 2000);
//                        }
//                        break;
//                }

            }
        });
    }

    private void initAdapter() {
        adapter = new Search_PagerAdapter(context);
        pager.setAdapter(adapter);
    }

    private void initRadio() {
        for (int i = 0; i < moreList.size() / 2; i++) {
            RadioButton rb = new RadioButton(context);
            rb.setId(i);
            rb.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int bei = pager.getCurrentItem() / 5;
                    pager.setCurrentItem(bei * 5 + v.getId());
                }
            });
//          设置 selecter
//          rb.setButtonDrawable();
            rg.addView(rb);
        }
        rg.check(0);
    }

    private void initView(View view) {
        pager = (ViewPager) view.findViewById(R.id.viewpager_more);
        rg = (RadioGroup) view.findViewById(R.id.radiogroup);

    }

    public void updatePager(ArrayList<MoreBean.DataBean.TopicBean> moreList, ImageLoader loader) {
        this.moreList = moreList;
        //初始化RadioButton
        initRadio();
        //更新viewpager的显示
        for (int i = 0; i < moreList.size() / 2; i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.search_pager_item, null);
            ImageView iv1 = (ImageView) view.findViewById(R.id.pager_iv1);
            ImageView iv2 = (ImageView) view.findViewById(R.id.pager_iv2);
            //存储iv上要显示的图片的网址
            iv1.setTag(moreList.get(i * 2).getFocus_picture_path());
            iv2.setTag(moreList.get(i * 2 + 1).getFocus_picture_path());
            pagerList.add(view);

        }
        adapter.setPagerList(pagerList, loader);
        adapter.notifyDataSetChanged();
        startSelect();
    }
}
