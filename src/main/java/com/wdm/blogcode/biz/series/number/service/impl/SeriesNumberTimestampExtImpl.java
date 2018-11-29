package com.wdm.blogcode.biz.series.number.service.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.wdm.blogcode.biz.series.number.service.SeriesNumberService;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

/**
 * @author wdmyong
 */
@Service("timestampExtSeriesNumberService")
@Lazy
public class SeriesNumberTimestampExtImpl implements SeriesNumberService {

    private static final int RANDOM_CHAR_LENGTH = 7;

    @Override
    public String generateSeriesNumber() {
        // 1000线程，百万量级下，1秒扛不下，2秒足矣
        return System.currentTimeMillis() + randomAlphanumeric(RANDOM_CHAR_LENGTH);
    }
}
