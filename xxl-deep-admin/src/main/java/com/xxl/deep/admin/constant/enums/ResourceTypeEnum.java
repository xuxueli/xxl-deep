package com.xxl.deep.admin.constant.enums;

/**
 * user status
 * @author xuxueli
 */
public enum ResourceTypeEnum {

    MENU(0, "菜单"),
    BUTTOM(1, "按钮");

    private int value;
    private String desc;

    ResourceTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

}
