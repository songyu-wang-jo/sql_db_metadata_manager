package com.songyuwong.SQLDBMetamanager;

import com.songyuwong.sqldbmetadatamanager.exception.ConnectionException;
import com.songyuwong.sqldbmetadatamanager.mysql.Mysql8ConnManager;

public class ConnectionTest {
    public static void main(String[] args) throws ConnectionException {
        Mysql8ConnManager mysql8ConnManager = new Mysql8ConnManager();
        mysql8ConnManager.getConnection();
    }
}
