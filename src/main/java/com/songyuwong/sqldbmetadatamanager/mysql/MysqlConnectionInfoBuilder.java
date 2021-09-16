package com.songyuwong.sqldbmetadatamanager.mysql;

import com.songyuwong.sqldbmetadatamanager.connection.AbstractConnectionInfoBuilder;
import com.songyuwong.sqldbmetadatamanager.connection.BaseConnectionInfo;

public abstract class MysqlConnectionInfoBuilder extends AbstractConnectionInfoBuilder {

    MysqlConnectionInfo mysqlConnectionInfo = new MysqlConnectionInfo();

    @Override
    public BaseConnectionInfo buildBaseConnectionInfo() {
        setDriver();
        mysqlConnectionInfo.setUrl("jdbc:mysql://192.168.100.101:9536/HII?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=CONVERT_TO_NULL");
        mysqlConnectionInfo.setUsername("root");
        mysqlConnectionInfo.setPassword("Songyuwong@163.com");
        return mysqlConnectionInfo;
    }

    protected abstract void setDriver();

}
