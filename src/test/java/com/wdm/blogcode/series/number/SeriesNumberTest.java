package com.wdm.blogcode.series.number;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.LongStream;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.wdm.blogcode.BlogCodeApplication;
import com.wdm.blogcode.biz.series.number.model.Order;
import com.wdm.blogcode.biz.series.number.service.SeriesNumberService;
import com.wdm.blogcode.service.data.RedisService;

import static org.junit.Assert.assertTrue;

/**
 * @author wdmyong
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogCodeApplication.class)
@WebAppConfiguration
public class SeriesNumberTest {

    private volatile AtomicLong atomicLong = new AtomicLong();
    private static final int THREAD_POOL_NUM = 1000;
    private static final long TIMES = 1000000;

    @Resource(name = "redisSeriesNumberService")
    private SeriesNumberService redisSeriesNumberService;
    @Resource(name = "timestampSeriesNumberService")
    private SeriesNumberService timestampSeriesNumberService;
    @Resource(name = "timestampExtSeriesNumberService")
    private SeriesNumberService timestampExtSeriesNumberService;
    @Resource(name = "uuidSeriesNumberService")
    private SeriesNumberService uuidSeriesNumberService;
    @Resource(name = "uuidExtSeriesNumberService")
    private SeriesNumberService uuidExtSeriesNumberService;

    @Resource
    private RedisService redisService;

    @Before
    public void warmupRedis() {
        redisService.get("warmupTestKey");
    }

    @Test
    public void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_NUM);
        long now = System.currentTimeMillis();
        Map<String, Order> result = Maps.newConcurrentMap();
        LongStream.rangeClosed(1, TIMES).forEach(id -> executorService.submit(() -> {
            atomicLong.getAndIncrement();
            String seriesNumber = generateSeriesNumber(5);
            result.put(seriesNumber, new Order(id, seriesNumber, now, now));
        }));

        try {
            executorService.awaitTermination(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

        System.out.println(atomicLong.get());
        System.out.println(result.size());
        result.keySet().stream().findFirst().ifPresent(System.out::println);
        System.out.println(Iterables.getLast(result.keySet()));
        // result.values().forEach(System.out::println);

        assertTrue(result.size() == atomicLong.get());
    }

    private String generateSeriesNumber(int type) {
        final SeriesNumberService seriesNumberService;
        switch (type) {
            case 1:
                seriesNumberService = redisSeriesNumberService;
                break;
            case 2:
                seriesNumberService = timestampSeriesNumberService;
                break;
            case 3:
                seriesNumberService = timestampExtSeriesNumberService;
                break;
            case 4:
                seriesNumberService = uuidSeriesNumberService;
                break;
            case 5:
                seriesNumberService = uuidExtSeriesNumberService;
                break;
            default:
                seriesNumberService = redisSeriesNumberService;
                break;
        }
        return seriesNumberService.generateSeriesNumber();
    }
}
