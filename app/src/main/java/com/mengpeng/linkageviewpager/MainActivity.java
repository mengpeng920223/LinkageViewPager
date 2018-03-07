package com.mengpeng.linkageviewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mengpeng.linkageviewpager.widget.indicator.TabIndicatorView;
import com.mengpeng.linkageviewpager.widget.indicator.adapter.IndicatorViewPager;
import com.mengpeng.linkageviewpager.widget.indicator.adapter.MyAdapter;
import com.mengpeng.linkageviewpager.widget.indicator.bar.SpringBar;
import com.mengpeng.linkageviewpager.widget.indicator.listener.OnTransitionTextListener;
import com.mengpeng.linkageviewpager.widget.tab.LinkTab;
import com.mengpeng.linkageviewpager.widget.tab.RadioGroupTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioGroupTabLayout tabLayout;
    private TabIndicatorView tabIndicatorView;
    private ViewPager viewPager;

    private List<TextView> mViewList;
    private IndicatorViewPager indicatorViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tabIndicatorView = findViewById(R.id.tabIndicatorView);

        initTab();
    }

    private void initTab() {

        int[] colorID = {Color.parseColor("#33ff0000"), Color.parseColor("#33ffff00"),
                Color.parseColor("#3300ff00"), Color.parseColor("#3300ffff")};

        mViewList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            TextView textView = new TextView(this);
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            textView.setBackgroundColor(colorID[i]);
            mViewList.add(textView);
        }

        int selectColorId = Color.parseColor("#c8c8c8");
        int unSelectColorId = Color.parseColor("#999999");

        tabIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColorId, unSelectColorId));
        tabIndicatorView.setScrollBar(new SpringBar(this, selectColorId));

        indicatorViewPager = new IndicatorViewPager(tabIndicatorView, viewPager);
        indicatorViewPager.setAdapter(new MyAdapter(mViewList, this));

        indicatorViewPager.setCurrentItem(0, false);
        indicatorViewPager.setOnIndicatorPageChangeListener(new IndicatorViewPager.OnIndicatorPageChangeListener() {
            @Override
            public void onIndicatorPageChange(int preItem, int position) {
                tabLayout.setCurrentItem(position);
            }
        });


        List<LinkTab> list = new ArrayList<>();
        list.add(new LinkTab("测试1", R.drawable.maintab_1_selector));
        list.add(new LinkTab("测试2", R.drawable.maintab_2_selector));
        list.add(new LinkTab("测试3", R.drawable.maintab_3_selector));
        list.add(new LinkTab("测试4", R.drawable.maintab_4_selector));

        tabLayout.addTab(list);

        tabLayout.setOnRadioGroupTabLayoutClickListener(
                new RadioGroupTabLayout.setOnRadioGroupTabLayoutClickListener() {
                    @Override
                    public void onTabLayoutClickListener(int position) {
                        viewPager.setCurrentItem(position);
                    }
                });
    }
}
