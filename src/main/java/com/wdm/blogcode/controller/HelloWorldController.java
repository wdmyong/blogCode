package com.wdm.blogcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wdm.blogcode.service.data.RedisService;

@RestController
public class HelloWorldController {

    @Autowired
    private RedisService redisService;

    @RequestMapping("/hello")
    public String index(@RequestParam("key") String key) {
        redisService.incr(key);
        return redisService.get(key);
    }
}