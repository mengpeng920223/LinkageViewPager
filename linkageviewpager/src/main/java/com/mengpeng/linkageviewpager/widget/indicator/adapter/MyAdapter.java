package com.mengpeng.linkageviewpager.widget.indicator.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * 作者：MengPeng
 * 时间：2018/3/6 - 下午5:28
 * 说明：
 */
public class MyAdapter extends DefaultIndicatorAdapter {
    private List<View> list;
    private Context context;

    public MyAdapter(List<View> list, Context context) {
        super(list, context);
        this.list = list;
        this.context = context;
    }

    @Override
    public View getViewForPage(int position, View convertView, ViewGroup container) {





        return list.get(position);
    }
}
