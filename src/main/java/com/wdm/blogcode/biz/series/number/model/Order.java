package com.wdm.blogcode.biz.series.number.model;

import org.apache.commons.lang3.builder.ToStringStyle;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

/**
 * @author wdmyong
 */
public class Order {

    private long id; // 订单id
    private String seriesNumber; // 序列号id
    private long createTime;
    private long updateTime;

    public Order() {
    }

    public Order(long id, String seriesNumber, long createTime, long updateTime) {
        this.id = id;
        this.seriesNumber = seriesNumber;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(String seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString()  {
        return reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
