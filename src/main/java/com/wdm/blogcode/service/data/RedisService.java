package com.wdm.blogcode.service.data;

/**
 * @author wdmyong
 */
public interface RedisService {

    void del(String key);

    String get(String key);

    void pexpire(String key, long millis);

    long incr(String key);
}
