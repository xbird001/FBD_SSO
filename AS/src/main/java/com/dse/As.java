package com.dse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * token的保存方式是内存模式时，需要exclude = {RedisRepositoriesAutoConfiguration.class, RedisAutoConfiguration.class}
 * token是保存方式redis缓存模式时，去掉exclude即可，只需要一个@SpringBootApplication即可
 */
@SpringBootApplication(exclude = {RedisRepositoriesAutoConfiguration.class, RedisAutoConfiguration.class})
@EnableAuthorizationServer
public class As {
    public static void main(String[] args) {
        SpringApplication.run(As.class, args);
    }
}
