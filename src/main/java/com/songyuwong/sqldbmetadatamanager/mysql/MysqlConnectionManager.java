package com.songyuwong.sqldbmetadatamanager.mysql;

import com.songyuwong.sqldbmetadatamanager.connection.AbstractConnectionInfoBuilder;
import com.songyuwong.sqldbmetadatamanager.connection.AbstractConnectionManager;

public class MysqlConnectionManager extends AbstractConnectionManager {

    public MysqlConnectionManager(AbstractConnectionInfoBuilder abstractConnectionInfoBuilder) {
        super(abstractConnectionInfoBuilder);
    }

//    public MysqlConnectionManager(String databaseConfigName) {
//        super();
//    }
}
