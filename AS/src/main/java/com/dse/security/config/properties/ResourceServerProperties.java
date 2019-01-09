package com.dse.security.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.dse.rs")
public class ResourceServerProperties {

    /**
     * token保存策略
     */
     private TokenStoreType tokenStoreType = TokenStoreType.LOCAL_MEMORY;

     /**
     * 用户登录信息保存策略
     */
    private UserRegistryType userRegistryType = UserRegistryType.LOCAL_MEMORY;

    /**
     * 用户登录信息保存策略是本机内存模式时用于保存用户登录信息
     */
    private DseUserInMemeroy usersInMemory = new DseUserInMemeroy();

    public UserRegistryType getUserRegistryType() {
        return userRegistryType;
    }

    public void setUserRegistryType(UserRegistryType userRegistryType) {
        this.userRegistryType = userRegistryType;
    }

    public DseUserInMemeroy getUsersInMemory() {
        return usersInMemory;
    }

    public void setUsersInMemory(DseUserInMemeroy usersInMemory) {
        this.usersInMemory = usersInMemory;
    }

    public TokenStoreType getTokenStoreType() {
        return tokenStoreType;
    }

    public void setTokenStoreType(TokenStoreType tokenStoreType) {
        this.tokenStoreType = tokenStoreType;
    }
}
