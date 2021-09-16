package com.songyuwong.sqldbmetadatamanager.exception;

public class BaseConnectionInfoException extends ConnectionException {

    public BaseConnectionInfoException(String message) {
        super("数据库连接信息错误 << "+message);
    }

}
