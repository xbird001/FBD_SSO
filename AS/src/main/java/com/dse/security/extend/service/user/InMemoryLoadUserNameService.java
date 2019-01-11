package com.dse.security.extend.service.user;

import com.dse.security.config.properties.ResourceServerProperties;
import com.dse.security.config.properties.UserRegistryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 本地内存方式保存用户登录信息
 */
@Component
public class InMemoryLoadUserNameService implements LoadUserByUserNameService {

    @Autowired
    private DseUserStore dseUserStore;

    @Autowired
    private ResourceServerProperties resourceServerProperties;

    @Override
    public DseUserDetails doLoadUserByUsername(String username) {
        return dseUserStore.geDseUser(username);
    }

    @Override
    public boolean isSupport(UserRegistryType userRegistryType) {
        return "LOCAL_MEMORY".equals(userRegistryType.getType());
    }
}
