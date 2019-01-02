package com.dse.security.config;

import com.dse.security.config.properties.ResourceServerProperties;
import com.dse.security.config.properties.TokenStoreType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;


@Configuration
public class DseAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private ResourceServerProperties resourceServerProperties;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                    .withClient("dse1")
                    .secret("dse123456")
                    .authorizedGrantTypes("password", "refresh_token")
                    .scopes("app")
                    //设置token的过期时间，该设置的时间，对通过redis进行缓存同样适用
                    .accessTokenValiditySeconds(3600)
                    //设置refresh token的过期时间
                    .refreshTokenValiditySeconds(7200)
                .and()
                    .withClient("dse2")
                    .secret("dse223456")
                    .authorizedGrantTypes("password", "refresh_token")
                    .scopes("app")
                    //设置token的过期时间
                    .accessTokenValiditySeconds(3600)
                    //设置refresh token的过期时间
                    .refreshTokenValiditySeconds(7200);
    }

    /**
     * 不设置这个将没有密码模式(相当于是一个开关，设置了这个，才打开密码模式)
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
        if(TokenStoreType.REDIS.equals(resourceServerProperties.getTokenStoreType())) {
            endpoints.tokenStore(tokenStore());
        }
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }


}
