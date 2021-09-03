package com.songyuwong.sqldbmetadatamanager.connection;

import com.songyuwong.sqldbmetadatamanager.exception.ConnectionException;

import java.sql.Connection;

/**
 * 关系型数据库连接管理接口
 */
public interface IConnectionManager {

    Connection getConnection() throws ConnectionException;

}
