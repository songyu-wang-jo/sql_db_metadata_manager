package com.songyuwong.sqldbmetadatamanager.oracle;

import com.songyuwong.sqldbmetadatamanager.connection.BaseConnectionInfo;
import com.songyuwong.sqldbmetadatamanager.exception.BaseConnectionInfoException;
import com.songyuwong.sqldbmetadatamanager.util.StringUtils;

public class OracleConnectionInfo extends BaseConnectionInfo {

    public static final String REMARKS_REPORTING = "remarksReporting";

    public String getRemarksReporting() {
        return super.getProperty(REMARKS_REPORTING);
    }

    public void setRemarksReporting(String remarksReporting) {
        super.setProperty(REMARKS_REPORTING,remarksReporting);
    }

    @Override
    protected boolean checkExtraConnInfo() throws BaseConnectionInfoException {
        if (StringUtils.isBlank(getRemarksReporting())) throw new BaseConnectionInfoException("未设置开启字段注释解析，将无法读取字段的注释");
        return true;
    }
}
