package com.songyuwong.sqldbmetadatamanager.mysql;

import com.songyuwong.sqldbmetadatamanager.connection.AbstractConnectionInfoBuilder;
import com.songyuwong.sqldbmetadatamanager.connection.AbstractConnectionManager;
import com.songyuwong.sqldbmetadatamanager.connection.BaseConnectionInfo;
import com.songyuwong.sqldbmetadatamanager.exception.BaseConnectionInfoException;
import com.songyuwong.sqldbmetadatamanager.exception.ConnectionException;

public class MysqlConnectionManager extends AbstractConnectionManager {

    public MysqlConnectionManager(AbstractConnectionInfoBuilder abstractConnectionInfoBuilder) {
        super(abstractConnectionInfoBuilder);
    }

}
