package com.dse.security.config;

import com.dse.security.config.properties.ResourceServerProperties;
import com.dse.security.extend.service.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

@Configuration
public class DseBeanConfig {

    @Autowired(required = false)
    private DseUserDetailsAdditionalService dseUserDetailsAdditionalService;

    @Autowired
    private ResourceServerProperties resourceServerProperties;

    @Autowired
    private DseUserStore dseUserStore;

    @Autowired
    private List<LoadUserByUserNameService> loadUserByUserNameServiceList;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Bean
    @ConditionalOnMissingBean(name = "dseUserDetailsService")
    public UserDetailsService dseUserDetailsService() {
        DseUserDetailsService dseUserDetailsService = new DseUserDetailsService(resourceServerProperties, dseUserStore, dseUserDetailsAdditionalService);
        dseUserDetailsService.setLoadUserByUserNameServiceList(loadUserByUserNameServiceList);
        return dseUserDetailsService;
    }

}
