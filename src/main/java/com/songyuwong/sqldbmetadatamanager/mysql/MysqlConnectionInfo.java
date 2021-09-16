package com.songyuwong.sqldbmetadatamanager.mysql;

import com.songyuwong.sqldbmetadatamanager.connection.BaseConnectionInfo;

public class MysqlConnectionInfo extends BaseConnectionInfo {

    @Override
    protected boolean checkExtraConnInfo() {
        return true;
    }
}
