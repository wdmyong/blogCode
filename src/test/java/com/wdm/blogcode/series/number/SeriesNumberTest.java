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
    private static final long TIMES = 100000;

    @Resource(name = "redisServiceNumberService")
    private SeriesNumberService redisServiceNumberService;

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
        Map<Long, Order> result = Maps.newConcurrentMap();
        LongStream.rangeClosed(1, TIMES).forEach(id -> executorService.submit(() -> {
            atomicLong.getAndIncrement();
            Order order = new Order(id, redisServiceNumberService.generateSeriesNumber(), now, now);
            result.put(id, order);
        }));

        // 等待并行执行完
        try {
            executorService.awaitTermination(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

        System.out.println(atomicLong.get());
        System.out.println(result.size());
        // result.values().forEach(System.out::println);

        assertTrue(TIMES == atomicLong.get());
    }
}
