package com.songyuwong.sqldbmetadatamanager.oracle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.songyuwong.sqldbmetadatamanager.connection.AbstractConnectionInfoBuilder;
import com.songyuwong.sqldbmetadatamanager.connection.AbstractConnectionManager;
import com.songyuwong.sqldbmetadatamanager.connection.BaseConnectionInfo;
import com.songyuwong.sqldbmetadatamanager.exception.ConnectionException;
import com.songyuwong.sqldbmetadatamanager.model.OracleConnectionBaseInfo;

import java.io.*;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.Arrays;

public class OracleConnectionManager extends AbstractConnectionManager {

    public OracleConnectionManager(AbstractConnectionInfoBuilder abstractConnectionInfoBuilder) {
        super(abstractConnectionInfoBuilder);
    }

    public OracleConnectionManager(String databaseConfigName) throws ConnectException {
        super(createConnInfoBuilderByPropertyFileName(databaseConfigName));
    }

    private static AbstractConnectionInfoBuilder createConnInfoBuilderByPropertyFileName(String db) throws ConnectException {
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = ClassLoader.getSystemResource("setting.json").openStream();
            int len = 12;
            char[] buffer = new char[len];
            int off = 0;
            StringBuilder stringBuilder = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((len = bufferedReader.read(buffer, off, len)) != -1) {
                if (len==1024){
                    stringBuilder.append(buffer);
                }else {
                    stringBuilder.append(Arrays.copyOf(buffer,len));
                }
            }
            String text = stringBuilder.toString();
            System.out.println("配置信息>>\n"+text+"\n<<配置信息");
            JSONObject jsonObject = JSON.parseObject(text);
            JSONObject database = jsonObject.getJSONObject("database");
            OracleConnectionBaseInfo oracleConnectionBaseInfo = database.getObject(db, OracleConnectionBaseInfo.class);
            if (oracleConnectionBaseInfo==null){
                throw new ConnectException("未找到对应的数据库配置<<"+db);
            }
            return new AbstractConnectionInfoBuilder() {
                @Override
                public BaseConnectionInfo buildBaseConnectionInfo() {
                    OracleConnectionInfo oracleConnectionInfo = new OracleConnectionInfo();
                    oracleConnectionInfo.setRemarksReporting(String.valueOf(oracleConnectionBaseInfo.getRemarksReporting()));
                    oracleConnectionInfo.setPassword(oracleConnectionBaseInfo.getPassword());
                    oracleConnectionInfo.setUsername(oracleConnectionBaseInfo.getUser());
                    oracleConnectionInfo.setUrl(oracleConnectionBaseInfo.getUrl());
                    oracleConnectionInfo.setDriverClassName(oracleConnectionBaseInfo.getDriver());
                    return oracleConnectionInfo;
                }
            };
        } catch (IOException e) {
            throw new ConnectException("读取配置文件失败：" + e.getMessage());
        }finally {
            try {
                assert inputStream != null;
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                assert bufferedReader != null;
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            OracleConnectionManager oracleConnectionManager = new OracleConnectionManager("test_oracle");
            try {
                System.out.println(oracleConnectionManager.getConnection().getMetaData().getDatabaseProductName());
            } catch (SQLException | ConnectionException e) {
                e.printStackTrace();
            }
        } catch (ConnectException e) {
            e.printStackTrace();
        }
    }

}
