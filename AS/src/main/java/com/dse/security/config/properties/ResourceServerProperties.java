package com.dse.security.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.dse.rs")
public class ResourceServerProperties {

    private TokenStoreType tokenStoreType = TokenStoreType.LOCAL_MEMORY;

    public TokenStoreType getTokenStoreType() {
        return tokenStoreType;
    }

    public void setTokenStoreType(TokenStoreType tokenStoreType) {
        this.tokenStoreType = tokenStoreType;
    }
}
