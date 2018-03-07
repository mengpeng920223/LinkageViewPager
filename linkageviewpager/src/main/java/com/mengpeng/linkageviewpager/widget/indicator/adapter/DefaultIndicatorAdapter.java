package com.mengpeng.linkageviewpager.widget.indicator.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mengpeng.linkageviewpager.R;

import java.util.List;

/**
 * 作者：MengPeng
 * 时间：2018/3/6 - 下午5:21
 * 说明：
 */
public abstract class DefaultIndicatorAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {
    private List<View> list;
    private Context context;

    public DefaultIndicatorAdapter(List<View> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.indicator_item, container, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText("●");
        textView.setTextColor(Color.parseColor("#999999"));
        textView.setPadding(4, 0, 4, 0);
        textView.setTextSize(10);
        return convertView;
    }
}
