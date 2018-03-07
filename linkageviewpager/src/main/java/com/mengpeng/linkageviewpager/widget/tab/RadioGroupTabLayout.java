package com.mengpeng.linkageviewpager.widget.tab;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mengpeng.linkageviewpagers.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：MengPeng
 * 时间：2018/3/5 - 下午7:09
 * 说明：
 */
public class RadioGroupTabLayout extends RelativeLayout {

    private Context context;
    private RadioGroup container;
    private RelativeLayout radioGroupTabLayout;
    private TextView line;
    private List<RadioButton> list;

    // RadioGroupTabLayout的背景颜色
    private int backgroundColor = Color.WHITE;
    //默认字体颜色
    private int defaultTextColor = Color.parseColor("#666666");
    //默认选中字体颜色
    private int selectedTextColor = Color.parseColor("#0076ff");
    //默认padding
    private int padding = 10;
    //线的颜色
    private int lineColor = Color.parseColor("#dadada");
    //默认选中的下标
    private int defaultSelectIndex = 0;
    //默认字体大小
    private int textSize = 12;

    private setOnRadioGroupTabLayoutClickListener listener;

    public interface setOnRadioGroupTabLayoutClickListener {
        void onTabLayoutClickListener(int position);
    }

    public void setOnRadioGroupTabLayoutClickListener(setOnRadioGroupTabLayoutClickListener listener) {
        this.listener = listener;
    }

    public RadioGroupTabLayout(Context context) {
        this(context, null);
    }

    public RadioGroupTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadioGroupTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView(attrs, defStyleAttr);
    }

    private void initView(AttributeSet attrs, int defStyleAttr) {
        View view = LayoutInflater.from(context).inflate(R.layout.radiogroup_tablayout, this);

        line = view.findViewById(R.id.radioGroupTabLayout_line);
        radioGroupTabLayout = view.findViewById(R.id.radioGroupTabLayout);
        container = view.findViewById(R.id.radioGroupTabLayout_container);

        TypedArray array = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.RadioGroupTabLayout, defStyleAttr, 0);

        list = new ArrayList<>();

        int indexCount = array.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = array.getIndex(i);
            if (index == R.styleable.RadioGroupTabLayout_tabLayoutBackgroundColor) {
                backgroundColor = array.getColor(index, Color.WHITE);
            } else if (index == R.styleable.RadioGroupTabLayout_tabLayoutDefaultTextColor) {
                defaultTextColor = array.getColor(index, Color.parseColor("#666666"));
            } else if (index == R.styleable.RadioGroupTabLayout_tabLayoutSelectedTextColor) {
                selectedTextColor = array.getColor(index, Color.parseColor("#0076ff"));
            } else if (index == R.styleable.RadioGroupTabLayout_tabLayoutTextSize) {
                textSize = array.getInt(index, 12);
            } else if (index == R.styleable.RadioGroupTabLayout_tabLayoutPadding) {
                padding = array.getInt(index, 10);
            } else if (index == R.styleable.RadioGroupTabLayout_tabLayoutLineColor) {
                lineColor = array.getColor(index, Color.parseColor("#dadada"));
            } else if (index == R.styleable.RadioGroupTabLayout_tabLayoutDefaultSelectIndex) {
                defaultSelectIndex = array.getInt(index, 0);
            }
        }

        radioGroupTabLayout.setBackgroundColor(backgroundColor);
        line.setBackgroundColor(lineColor);

        array.recycle();

    }

    public void addTab(@NotNull List<LinkTab> tabList) {
        //先清空容器
        container.removeAllViews();
        list.clear();

        //获取屏幕的宽度，用来计算每个tab的宽度
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        int width = widthPixels / tabList.size();

        for (int i = 0; i < tabList.size(); i++) {

            LinkTab linkTab = tabList.get(i);
            final RadioButton button = new RadioButton(context);

            //设置内容
            String content = TextUtils.isEmpty(linkTab.getTabName()) ?
                    context.getResources().getString(linkTab.getTabNameID()) : linkTab.getTabName();
            button.setText(content);
            //去点左边的按钮样式
            button.setButtonDrawable(null);
            //设置宽度
            button.setWidth(width);
            //设置高度
            button.setHeight(dp2px(context, 49.5f));
            //设置字体大小
            button.setTextSize(textSize);
            button.setTextColor(defaultTextColor);

            button.setPadding(0, padding, 0, 6);

            Drawable drawable = ContextCompat.getDrawable(context, linkTab.getTabIcon());
            button.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawable, null, null);

            button.setGravity(Gravity.CENTER);

            container.addView(button);
            list.add(button);

            button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        button.setTextColor(selectedTextColor);
                    } else {
                        button.setTextColor(defaultTextColor);
                    }
                }
            });
        }
        //默认选中
        ((RadioButton) container.getChildAt(defaultSelectIndex > tabList.size() ? 0 : defaultSelectIndex)).setChecked(true);

        container.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getId() == checkedId) {
                        if (null != listener) {
                            listener.onTabLayoutClickListener(i);
                        }
                        return;
                    }
                }
            }
        });
    }

    private static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }

    public void setRadioGroupTabLayoutBackground(@NotNull int colorID) {
        radioGroupTabLayout.setBackgroundColor(ContextCompat.getColor(context, colorID));
    }

    public void setRadioGroupTabLayoutBackground(@NotNull String color) {
        if (TextUtils.isEmpty(color)) {
            throw new NullPointerException("color can not null");
        }
        if (color.startsWith("#") || color.length() != 7 && color.length() != 9) {
            throw new NullPointerException("所填写的颜色值必须以‘#’开头，并且必须是7位（例如#ffffff）或者9位（例如#33ff0000） ");
        }
        radioGroupTabLayout.setBackgroundColor(Color.parseColor(color));
    }

    public void setCurrentItem(int position) {
        if (position > list.size()) {
            ((RadioButton) container.getChildAt(list.size())).setChecked(true);
        } else if (position < 0) {
            ((RadioButton) container.getChildAt(0)).setChecked(true);
        } else {
            ((RadioButton) container.getChildAt(position)).setChecked(true);
        }
    }

}
