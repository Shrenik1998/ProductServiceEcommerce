package com.scaler.backendprojectecomm.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;
import java.util.Arrays;

//library classes cannot be updated
//we cannot add @annotations for the library classes
//@Configuration tells been that this is a special class using which beans are created
@Configuration
public class ApplicationConfig {

    //spring will create object of RestTemplate and add it to the application context
    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        return restTemplate;
    }

    @Bean
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
