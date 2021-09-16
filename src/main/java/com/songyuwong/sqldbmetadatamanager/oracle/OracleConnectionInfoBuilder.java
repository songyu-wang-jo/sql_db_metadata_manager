package com.songyuwong.sqldbmetadatamanager.oracle;

import com.songyuwong.sqldbmetadatamanager.connection.AbstractConnectionInfoBuilder;
import com.songyuwong.sqldbmetadatamanager.connection.BaseConnectionInfo;

public class OracleConnectionInfoBuilder extends AbstractConnectionInfoBuilder {

    @Override
    public BaseConnectionInfo buildBaseConnectionInfo() {
        OracleConnectionInfo oracleConnectionInfo = new OracleConnectionInfo();
        oracleConnectionInfo.setDriverClassName("oracle.jdbc.OracleDriver");
        oracleConnectionInfo.setUrl("jdbc:oracle:thin:@183.62.250.66:58013:TEST");
        oracleConnectionInfo.setUsername("JHIDS");
        oracleConnectionInfo.setPassword("JHIDS");
        oracleConnectionInfo.setRemarksReporting("true");
        return oracleConnectionInfo;
    }
}
