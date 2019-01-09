package com.dse.security.config;

import com.dse.security.config.properties.ResourceServerProperties;
import com.dse.security.config.properties.UserRegistryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class DseUserDetailsService implements UserDetailsService {


    @Autowired
    private ResourceServerProperties resourceServerProperties;

    @Autowired
    private DseUserStore dseUserStore;

    @Autowired(required = false)
    private DseUserDetailsAdditionalService dseUserDetailsAdditionalService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        DseUserDetails user = null;
        //此处可以注入我们自己的本进行数据库查询
        if (resourceServerProperties.getUserRegistryType().equals(UserRegistryType.LOCAL_MEMORY)) {
            user = dseUserStore.geDseUser(username);
        } else {
            //数据库保存储方式
        }
        DseUserDetails user4Rs = null;
        if (user != null) {
            user4Rs = new DseUserDetails(user.getUsername(), user.getPassword(), true, true, true, true, user.getAuthorities());
            if (dseUserDetailsAdditionalService != null) {
                user.setAdditional(dseUserDetailsAdditionalService.getAdditional(username));
                user4Rs.setAdditional(user.getAdditional());
            }
        }
        return user4Rs;
    }


}
