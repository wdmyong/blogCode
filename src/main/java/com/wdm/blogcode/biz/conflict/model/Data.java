package com.wdm.blogcode.biz.conflict.model;

/**
 * @author wdmyong
 */
public interface Data {

    default boolean isMusic() {
        return false;
    }

    default boolean isMovie() {
        return false;
    }

    default boolean isTv() {
        return false;
    }

    default boolean isGame() {
        return false;
    }
}
