package com.songyuwong.sqldbmetadatamanager.mysql;

public class Mysql8ConnectionInfoBuilder extends MysqlConnectionInfoBuilder{
    @Override
    protected void setDriver() {
        mysqlConnectionInfo.setDriverClassName("com.mysql.cj.jdbc.Driver");
    }
}
