package com.txxw.sso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/8/25
 * @description:
 **/
@Configuration
public class RemoteConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
