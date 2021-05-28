package com.appeal.api.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class RedisService {

    private final StringRedisTemplate redisTemplate;

    @Transactional
    public void saveCodeEmail(String code, String email) {
        redisTemplate.opsForValue().set(code, email, 12L, TimeUnit.HOURS);
    }

    public Optional<String> getEmail(String code) {
//        return Optional.ofNullable(
//                redisTemplate.opsForValue().get(code)
//        );
        String string = redisTemplate.opsForValue().get(code);
        return Optional.ofNullable(string);
    }
}