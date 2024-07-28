package com.xxl.deep.admin.constant.enums;

/**
 * user status
 * @author xuxueli
 */
public enum ResourceStatuEnum {

    NORMAL(0, "正常"),
    INACTIVE(1, "停用");

    private int status;
    private String desc;

    ResourceStatuEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

}
