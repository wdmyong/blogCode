package com.wdm.blogcode.biz.series.number.service.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.wdm.blogcode.biz.series.number.service.SeriesNumberService;

/**
 * @author wdmyong
 */
@Service("timestampSeriesNumberService")
@Lazy
public class SeriesNumberTimestampImpl implements SeriesNumberService {

    private static final String SERIES_PREFIX = "NUMBER_";

    @Override
    public String generateSeriesNumber() {
        // 几乎没有并发能力，单线程执行快了都会重复
        return SERIES_PREFIX + System.currentTimeMillis();
    }
}
