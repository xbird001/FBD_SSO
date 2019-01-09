package com.dse.security.config;

import com.dse.security.config.properties.ResourceServerProperties;
import com.dse.security.extend.service.DseUserDetailsAdditionalService;
import com.dse.security.extend.service.DseUserDetailsService;
import com.dse.security.extend.service.LoadUserByUserNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

@Configuration
public class DseUserDetailsServiceConfig {

    @Autowired(required = false)
    private DseUserDetailsAdditionalService dseUserDetailsAdditionalService;

    @Autowired
    private ResourceServerProperties resourceServerProperties;

    @Autowired
    private DseUserStore dseUserStore;

    @Autowired
    private List<LoadUserByUserNameService> loadUserByUserNameServiceList;


    @Bean
    @ConditionalOnMissingBean(name="dseUserDetailsService")
    public UserDetailsService dseUserDetailsService() {
        DseUserDetailsService dseUserDetailsService = new DseUserDetailsService(resourceServerProperties,dseUserStore,dseUserDetailsAdditionalService);
        dseUserDetailsService.setLoadUserByUserNameServiceList(loadUserByUserNameServiceList);
        return dseUserDetailsService;
    }
}
