package com.wdm.blogcode.biz.series.number.service.impl;

import java.util.UUID;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.wdm.blogcode.biz.series.number.service.SeriesNumberService;

/**
 * @author wdmyong
 */
@Service("uuidSeriesNumberService")
@Lazy
public class SeriesNumberUuidImpl implements SeriesNumberService {

    @Override
    public String generateSeriesNumber() {
        // 1000线程，百万量级下，4秒能搞定，3秒不行
        return UUID.randomUUID().toString();
    }
}
