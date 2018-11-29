package com.wdm.blogcode.biz.series.number.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.wdm.blogcode.biz.series.number.service.SeriesNumberService;
import com.wdm.blogcode.service.data.RedisService;
import com.wdm.blogcode.utils.FormatUtil;

/**
 * @author wdmyong
 * redis实现生成序列号
 * 利用其它自增生成器都可实现，如Mysql自增主键等
 * 其特点在于：依赖分布式存储、暴露了id、方案简单
 */
@Service("redisSeriesNumberService")
@Lazy
public class SeriesNumberRedisImpl implements SeriesNumberService {

    private static final String SERIES_NUMBER_KEY = "series_number_key:unique";

    @Autowired
    private RedisService redisService;

    @Override
    public String generateSeriesNumber() {
        // 1000线程，百万量级，21秒扛不住，22秒可以
        return FormatUtil.format20FixedLength(redisService.incr(SERIES_NUMBER_KEY));
    }
}
