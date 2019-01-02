package com.dse.config;

import com.dse.handler.DseAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.stereotype.Component;

@Component
public class DseOAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Bean
    public DseAuthenticationEntryPoint dseAuthenticationEntryPoint() {
        return new DseAuthenticationEntryPoint();
    }

    @Bean
    public RemoteTokenServices remoteTokenServices() {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setClientId("dse1");
        remoteTokenServices.setClientSecret("dse123456");
        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:8070//oauth/check_token");
        return remoteTokenServices;
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(dseAuthenticationEntryPoint());
    }
}
