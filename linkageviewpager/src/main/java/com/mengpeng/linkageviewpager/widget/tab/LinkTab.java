package com.mengpeng.linkageviewpager.widget.tab;

/**
 * 作者：MengPeng
 * 时间：2018/3/5 - 下午7:30
 * 说明：
 */
public class LinkTab {

    private String tabName;
    private int tabIcon;
    private int tabNameID;

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public int getTabIcon() {
        return tabIcon;
    }

    public void setTabIcon(int tabIcon) {
        this.tabIcon = tabIcon;
    }

    public int getTabNameID() {
        return tabNameID;
    }

    public void setTabNameID(int tabNameID) {
        this.tabNameID = tabNameID;
    }

    public LinkTab(String tabName, int tabIcon) {
        this.tabName = tabName;
        this.tabIcon = tabIcon;
    }

    public LinkTab(int tabIcon, int tabNameID) {
        this.tabIcon = tabIcon;
        this.tabNameID = tabNameID;
    }


}
