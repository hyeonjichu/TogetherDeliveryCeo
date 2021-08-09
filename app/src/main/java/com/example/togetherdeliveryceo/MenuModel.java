package com.example.togetherdeliveryceo;

import java.io.Serializable;

public class MenuModel implements Serializable {
    String menuInfo;
    String menuName;
    String menuPrice;
    boolean selected;

    //private boolean isSelected;
    public MenuModel(){}


    public MenuModel(String menuInfo, String menuName, String menuPrice, boolean selected) {
        this.menuInfo = menuInfo;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getMenuInfo(Object menuInfo) {
        return this.menuInfo;
    }

    public void setMenuInfo(String menuInfo) {
        this.menuInfo = menuInfo;
    }

    public String getMenuName(Object menuName) {
        return this.menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPrice(Object menuPrice) {
        return this.menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }
}
