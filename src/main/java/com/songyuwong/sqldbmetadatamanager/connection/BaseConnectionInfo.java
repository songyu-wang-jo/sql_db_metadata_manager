package com.songyuwong.sqldbmetadatamanager.connection;

import com.songyuwong.sqldbmetadatamanager.exception.BaseConnectionInfoException;
import com.songyuwong.sqldbmetadatamanager.util.StringUtils;

import java.util.Properties;

public abstract class BaseConnectionInfo extends Properties {

    public static final String USERNAME = "user";

    public static final String PASSWORD = "password";

    public static final String DRIVER = "driver";

    public static final String URL = "url";

    public boolean checkBaseConnectionInfo() throws BaseConnectionInfoException {
        if (StringUtils.isBlank(getDriverClassName())) throw new BaseConnectionInfoException("数据库驱动未设置");
        if (StringUtils.isBlank(getUrl())) throw new BaseConnectionInfoException("数据库连接URL未设置");
        if (StringUtils.isBlank(getUsername())) throw new BaseConnectionInfoException("数据库连接用户名未设置");
        if (StringUtils.isBlank(getPassword())) throw new BaseConnectionInfoException("数据库连接密码未设置");
        return checkExtraConnInfo();
    }

    protected abstract boolean checkExtraConnInfo() throws BaseConnectionInfoException;

    public String getDriverClassName() {
        return super.getProperty(DRIVER);
    }

    public void setDriverClassName(String driverClassName) {
        super.setProperty(DRIVER, driverClassName);
    }

    public String getUrl() {
        return super.getProperty(URL);
    }

    public void setUrl(String url) {
        super.setProperty(URL, url);
    }

    public String getUsername() {
        return super.getProperty(USERNAME);
    }

    public void setUsername(String username) {
        super.setProperty(USERNAME,username);
    }

    public String getPassword() {
        return super.getProperty(PASSWORD);
    }

    public void setPassword(String password) {
        super.setProperty(PASSWORD,password);
    }
}
