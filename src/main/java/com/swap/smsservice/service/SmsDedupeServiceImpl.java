package com.swap.smsservice.service.impl;

import com.swap.smsservice.service.SmsDedupeService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class SmsDedupeServiceImpl implements SmsDedupeService {

    private final StringRedisTemplate redisTemplate;

    public SmsDedupeServiceImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean isDuplicate(String message) {
        String key = "sms:" + message.hashCode();

        Boolean exists = redisTemplate.hasKey(key);
        if (Boolean.TRUE.equals(exists)) {
            return true;
        }

        redisTemplate.opsForValue().set(key, "1", Duration.ofDays(30));
        return false;
    }
}