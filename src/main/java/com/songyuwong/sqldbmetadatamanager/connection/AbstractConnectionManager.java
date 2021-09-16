package com.songyuwong.sqldbmetadatamanager.connection;

import com.songyuwong.sqldbmetadatamanager.exception.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 关系型数据库连接管理抽象类
 */
public abstract class AbstractConnectionManager implements IConnectionManager {

    protected AbstractConnectionInfoBuilder abstractConnectionInfoBuilder;

    private AbstractConnectionManager() {
    }

    public AbstractConnectionManager(AbstractConnectionInfoBuilder abstractConnectionInfoBuilder) {
        this.abstractConnectionInfoBuilder = abstractConnectionInfoBuilder;
    }

    private void prepareConnection() throws ConnectionException {
        if (abstractConnectionInfoBuilder.hasConnectionInitSucceed()) {
            try {
                DriverManager.getDriver(abstractConnectionInfoBuilder.getUrl());
            } catch (SQLException e) {
                try {
                    System.out.println("未找到对应url驱动 >> 注册驱动");
                    Class.forName(abstractConnectionInfoBuilder.getDriverClassName());
                } catch (ClassNotFoundException ex) {
                    throw new ConnectionException("驱动注册失败，请检查驱动类名和对应的依赖 << ".concat(e.getLocalizedMessage()));
                }
            }
        }
    }

    @Override
    public Connection getConnection() throws ConnectionException {
        prepareConnection();
        System.out.println("开始获取连接...");
        try {
            Connection connection = DriverManager.getConnection(abstractConnectionInfoBuilder.getUrl(), abstractConnectionInfoBuilder.getProp());
            System.out.println("获取连接成功...");
            return connection;
        } catch (SQLException e) {
            throw new ConnectionException("获取连接失败，请核对连接信息是否正确 << ".concat(e.getLocalizedMessage()));
        }
    }

}
