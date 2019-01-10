package com.dse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication(exclude = {/*RedisRepositoriesAutoConfiguration.class, RedisAutoConfiguration.class*/})
@EnableAuthorizationServer
public class As {
    public static void main(String[] args) {
        SpringApplication.run(As.class, args);
    }
}
