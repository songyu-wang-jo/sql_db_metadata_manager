package com.songyuwong.sqldbmetadatamanager.mysql;

import com.songyuwong.sqldbmetadatamanager.connection.AbstractConnectionManager;
import com.songyuwong.sqldbmetadatamanager.connection.BaseConnectionInfo;
import com.songyuwong.sqldbmetadatamanager.exception.ConnectionException;

public abstract class MysqlConnectionManager extends AbstractConnectionManager {

    public MysqlConnectionManager() {
        super(new BaseConnectionInfo());
    }

    @Override
    protected boolean checkBaseConnectionInfo() throws ConnectionException {
        if (!provideDriverClassName()) throw new ConnectionException("连接信息不正确请核对信息是否设置 << 驱动未设置");
        if (!providePassword()) throw new ConnectionException("连接信息不正确请核对信息是否设置 << 密码未设置");
        if (!provideUsername()) throw new ConnectionException("连接信息不正确请核对信息是否设置 << 用户名未设置");
        if (!provideUrl()) throw new ConnectionException("连接信息不正确请核对信息是否设置 << 连接url未设置");
        return true;
    }

    protected abstract boolean provideDriverClassName();

    protected abstract boolean providePassword();

    protected abstract boolean provideUsername();

    protected abstract boolean provideUrl();

}
