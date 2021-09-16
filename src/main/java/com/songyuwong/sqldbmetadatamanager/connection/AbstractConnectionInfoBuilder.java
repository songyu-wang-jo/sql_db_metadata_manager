package com.songyuwong.sqldbmetadatamanager.connection;

import com.songyuwong.sqldbmetadatamanager.exception.BaseConnectionInfoException;

import java.util.Properties;

public abstract class AbstractConnectionInfoBuilder implements IConnectionInfoBuilder {

    private BaseConnectionInfo baseConnectionInfo;

    boolean hasConnectionInitSucceed() throws BaseConnectionInfoException {
        baseConnectionInfo = buildBaseConnectionInfo();
        return baseConnectionInfo.checkBaseConnectionInfo();
    }

    String getUrl(){
        return baseConnectionInfo.getUrl();
    }

    Properties getProp(){
        return baseConnectionInfo;
    }

    String getDriverClassName(){
        return baseConnectionInfo.getDriverClassName();
    }
}
