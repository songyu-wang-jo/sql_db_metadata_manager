package com.songyuwong.sqldbmetadatamanager.mysql;

public class Mysql8ConnManager extends MysqlConnectionManager{


    @Override
    protected boolean provideDriverClassName() {
        baseConnectionInfo.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return true;
    }

    @Override
    protected boolean providePassword() {
        baseConnectionInfo.setPassword("Songyuwong@163.com");
        return true;
    }

    @Override
    protected boolean provideUsername() {
        baseConnectionInfo.setUsername("root");
        return true;
    }

    @Override
    protected boolean provideUrl() {
        baseConnectionInfo.setUrl("jdbc:mysql://192.168.100.100:9536/HII?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=CONVERT_TO_NULL");
        return true;
    }
}
