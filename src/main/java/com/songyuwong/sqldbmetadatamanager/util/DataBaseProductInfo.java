package com.songyuwong.sqldbmetadatamanager.util;

import com.songyuwong.sqldbmetadatamanager.oracle.OracleConnectionInfoBuilder;
import com.songyuwong.sqldbmetadatamanager.oracle.OracleConnectionManager;

public class DataBaseProductInfo {
    public static void main(String[] args) {
        OracleConnectionManager oracleConnectionManager = new OracleConnectionManager(new OracleConnectionInfoBuilder());
    }
}
