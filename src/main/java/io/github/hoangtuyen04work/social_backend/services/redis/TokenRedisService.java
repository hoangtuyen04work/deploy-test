package io.github.hoangtuyen04work.social_backend.services.redis;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.hoangtuyen04work.social_backend.exception.AppException;
import io.github.hoangtuyen04work.social_backend.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TokenRedisService {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void saveToken(String id, String token) throws JsonProcessingException {
        String key = "access_token:" + id;
        String json = objectMapper.writeValueAsString(token);
        redisTemplate.opsForValue().set(key, json, Duration.ofHours(24));
    }

    public String getToken(String id) throws AppException {
        String key  = "access_token:" + id;
        return (String)redisTemplate.opsForValue().get(key);
    }

    public void deleteToken(String id){
        String key = "access_token:" + id;
        redisTemplate.delete(key);
    }

}
