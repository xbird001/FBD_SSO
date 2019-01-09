package com.dse.security.extend.service;

import com.dse.security.config.DseUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public abstract class AbstractDseUserDetailsService implements UserDetailsService {

    private DseUserDetailsAdditionalService dseUserDetailsAdditionalService;

    protected abstract DseUserDetails doLoadUserByUsername(String username);

    public AbstractDseUserDetailsService() {}

    public AbstractDseUserDetailsService(DseUserDetailsAdditionalService dseUserDetailsAdditionalService) {
        this.dseUserDetailsAdditionalService = dseUserDetailsAdditionalService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DseUserDetails user = doLoadUserByUsername(username);

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

    public DseUserDetailsAdditionalService getDseUserDetailsAdditionalService() {
        return dseUserDetailsAdditionalService;
    }

    public void setDseUserDetailsAdditionalService(DseUserDetailsAdditionalService dseUserDetailsAdditionalService) {
        this.dseUserDetailsAdditionalService = dseUserDetailsAdditionalService;
    }
}
