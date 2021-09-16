package com.songyuwong.sqldbmetadatamanager.mysql;

public class Mysql8ConnManager extends MysqlConnectionManager{

    public Mysql8ConnManager() {
        super(new Mysql8ConnectionInfoBuilder());
    }

}
