package com.dse.security.config.properties;

/**
 * 登录信息的保存方式
 *  LOCAL_MEMORY：本机内存保存：适用于开发环境
 *  DB：数据库：适用于生产环境
 *  对于还有其他的方式，可以通过继承UserRegistryType枚举类，添加新的枚举类，配合LoadUserByUserNameService接口实现类，扩展新的存储方式
 */
public enum UserRegistryType {

    /**
     * 内存模式
     */
    LOCAL_MEMORY,

    /**
     * 数据库模式保存
     */
    DB;
}
