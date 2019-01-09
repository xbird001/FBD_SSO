package com.dse.security.extend.service.user;

import com.dse.security.config.DseUserDetails;
import com.dse.security.config.properties.UserRegistryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 用户登录信息数据库方式保存
 */
@Component
public class DBLoadUserNameService implements LoadUserByUserNameService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public DseUserDetails doLoadUserByUsername(String username) {
        //暂时测试用
        return new DseUserDetails(username,bCryptPasswordEncoder.encode("123456"),true,true,true,true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,common,ROLE_USER"));
    }

    @Override
    public boolean isSupport(UserRegistryType userRegistryType) {
        return UserRegistryType.DB.equals(userRegistryType);
    }
}
