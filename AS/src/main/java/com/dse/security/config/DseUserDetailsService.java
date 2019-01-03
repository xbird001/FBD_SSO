package com.dse.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DseUserDetailsService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //此处可以注入我们自己的本进行数据库查询
        //User user = new User(username, bCryptPasswordEncoder.encode("123456"), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,commone,ROLE_USER"));
        DseUserDetails user = new DseUserDetails(username, bCryptPasswordEncoder.encode("123456"), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,commone,ROLE_USER"));
        Map<String,Object> additional = new HashMap();
        additional.put("name","xbird");
        additional.put("mobile","15871748910");
        user.setAdditional(additional);
        return user;
    }


}
