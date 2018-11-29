package com.wdm.blogcode.biz.series.number.service.impl;

import java.util.UUID;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.wdm.blogcode.biz.series.number.service.SeriesNumberService;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

/**
 * @author wdmyong
 */
@Service("uuidExtSeriesNumberService")
@Lazy
public class SeriesNumberUuidExtImpl implements SeriesNumberService {

    private static final int START_INDEX = 20;
    private static final int RANDOM_CHAR_LENGTH = 4;

    @Override
    public String generateSeriesNumber() {
        UUID uuid = UUID.randomUUID();
        String randomAlphanumeric = randomAlphanumeric(RANDOM_CHAR_LENGTH);
        /*
         * 1000线程，百万量级下，比较耗时，4秒内执行不完，5秒可以，多机部署可以解决耗时问题
         * 添加一个机器id，即可，都是本地操作
         */
        // return format16FixedLength(Math.abs(uuid.hashCode())) + randomAlphanumeric;
        /*
         * 1000线程，百万量级下，4秒内能执行完，但3秒内也执行不完
         */
        return uuid.toString().substring(START_INDEX) + randomAlphanumeric;
    }
}
