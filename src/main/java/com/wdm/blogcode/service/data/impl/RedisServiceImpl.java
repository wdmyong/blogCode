package com.wdm.blogcode.service.data.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.wdm.blogcode.service.data.RedisService;

/**
 * @author wdmyong
 */
@Service
@Lazy
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void pexpire(String key, long millis) {
        redisTemplate.expire(key, millis, TimeUnit.MILLISECONDS);
    }

    @Override
    public void incr(String key) {
        redisTemplate.opsForValue().increment(key);
    }
}
