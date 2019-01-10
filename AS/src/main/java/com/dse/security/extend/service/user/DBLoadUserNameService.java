package com.dse.security.extend.service.user;

import com.dse.security.config.properties.UserRegistryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户登录信息数据库方式保存
 */
@Component
public class DBLoadUserNameService implements LoadUserByUserNameService {

    @Autowired
    private DseUserDetailsDao dseUserDetailsDao;


    @Override
    public DseUserDetails doLoadUserByUsername(String username) {
        return dseUserDetailsDao.loadUserByUsername(username);
    }

    @Override
    public boolean isSupport(UserRegistryType userRegistryType) {
        return UserRegistryType.DB.equals(userRegistryType);
    }
}
