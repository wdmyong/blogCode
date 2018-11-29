package com.wdm.blogcode.biz.conflict.constant;

/**
 * @author wdmyong
 */
public enum BizType {
    UNKNOWN(0, ""),
    MUSIC(1, "音乐"),
    MOVIE(2, "电影"),
    TV(3, "电视"),
    GAME(4, "游戏"),
    ;

    private int id;
    private String desc;

    BizType(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }
}
