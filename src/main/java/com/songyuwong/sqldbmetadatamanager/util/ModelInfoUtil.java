package com.songyuwong.sqldbmetadatamanager.util;

import com.songyuwong.sqldbmetadatamanager.oracle.OracleConnectionInfoBuilder;
import com.songyuwong.sqldbmetadatamanager.oracle.OracleConnectionManager;

import java.sql.*;

public class ModelInfoUtil {

    public static void main(String[] args) throws Exception {
        OracleConnectionManager oracleConnectionManager = new OracleConnectionManager(new OracleConnectionInfoBuilder());
        Connection dbConnection = oracleConnectionManager.getConnection();
        DatabaseMetaData metaData = dbConnection.getMetaData();
        System.out.println("开始处理数据库表结构信息....");
        ResultSet tables = metaData.getTables(dbConnection.getCatalog(), dbConnection.getSchema(), "JH%", new String[]{"TABLE"});
        while (tables.next()) {
            System.out.println("开始处理表" + tables.getString("TABLE_NAME") + "....");
            ResultSetMetaData tablesMetaData = tables.getMetaData();
            System.out.println("++++++++++++ LABEL ++++++++++++");
            for (int i = 1; i <= tablesMetaData.getColumnCount(); i++) {
                System.out.print(tablesMetaData.getColumnLabel(i)+"\t\t");
            }
            System.out.println(" ");
            System.out.println("++++++++++++ LABEL ++++++++++++");
            System.out.println("++++++++++++ TYPE ++++++++++++");
            for (int i = 1; i <= tablesMetaData.getColumnCount(); i++) {
                System.out.print(tablesMetaData.getColumnType(i)+"\t\t");
            }
            System.out.println(" ");
            System.out.println("++++++++++++ TYPE ++++++++++++");
            System.out.println("++++++++++++ TYPENAME ++++++++++++");
            for (int i = 1; i <= tablesMetaData.getColumnCount(); i++) {
                System.out.print(tablesMetaData.getColumnTypeName(i)+"\t\t");
            }
            System.out.println(" ");
            System.out.println("++++++++++++ TYPENAME ++++++++++++");
            System.out.println("++++++++++++ CLASSNAME ++++++++++++");
            for (int i = 1; i <= tablesMetaData.getColumnCount(); i++) {
                System.out.print(tablesMetaData.getColumnClassName(i)+"\t\t");
            }
            System.out.println(" ");
            System.out.println("++++++++++++ CLASSNAME ++++++++++++");
            System.out.println("++++++++++++ DISPLAYSIZE ++++++++++++");
            for (int i = 1; i <= tablesMetaData.getColumnCount(); i++) {
                System.out.print(tablesMetaData.getColumnDisplaySize(i)+"\t\t");
            }
            System.out.println(" ");
            System.out.println("++++++++++++ DISPLAYSIZE ++++++++++++");
            System.out.println("++++++++++++ COLUMNNAME ++++++++++++");
            for (int i = 1; i <= tablesMetaData.getColumnCount(); i++) {
                System.out.print(tablesMetaData.getColumnName(i)+"\t\t");
            }
            System.out.println(" ");
            System.out.println("++++++++++++ COLUMNNAME ++++++++++++");
            System.out.println("++++++++++++ CATALOGNAME ++++++++++++");
            for (int i = 1; i <= tablesMetaData.getColumnCount(); i++) {
                System.out.print(tablesMetaData.getCatalogName(i)+"\t\t");
            }
            System.out.println(" ");
            System.out.println("++++++++++++ CATALOGNAME ++++++++++++");
            System.out.println("++++++++++++ TABLENAME ++++++++++++");
            for (int i = 1; i <= tablesMetaData.getColumnCount(); i++) {
                System.out.print(tablesMetaData.getTableName(i)+"\t\t");
            }
            System.out.println(" ");
            System.out.println("++++++++++++ TABLENAME ++++++++++++");
            System.out.println("++++++++++++ PRECISION ++++++++++++");
            for (int i = 1; i <= tablesMetaData.getColumnCount(); i++) {
                System.out.print(tablesMetaData.getPrecision(i)+"\t\t");
            }
            System.out.println(" ");
            System.out.println("++++++++++++ PRECISION ++++++++++++");
            System.out.println("++++++++++++ SCALE ++++++++++++");
            for (int i = 1; i <= tablesMetaData.getColumnCount(); i++) {
                System.out.print(tablesMetaData.getScale(i)+"\t\t");
            }
            System.out.println(" ");
            System.out.println("++++++++++++ SCALE ++++++++++++");
            System.out.println("++++++++++++ SCHEMANAME ++++++++++++");
            for (int i = 1; i <= tablesMetaData.getColumnCount(); i++) {
                System.out.print(tablesMetaData.getSchemaName(i)+"\t\t");
            }
            System.out.println(" ");
            System.out.println("++++++++++++ SCHEMANAME ++++++++++++");
            dealTableStrut(dbConnection, metaData, tables.getString("TABLE_NAME"));
        }
    }

    private static void dealTableStrut(Connection dbConnection, DatabaseMetaData metaData, String tableName) throws SQLException {
        ResultSet columns = metaData.getColumns(dbConnection.getCatalog(), dbConnection.getSchema(), tableName, "%");
        ResultSetMetaData columnsMetaData = columns.getMetaData();
        System.out.println("============ LABEL ===========");
        for (int i = 1; i <= columnsMetaData.getColumnCount(); i++) {
            System.out.print(columnsMetaData.getColumnLabel(i)+"\t\t");
        }
        System.out.println(" ");
        System.out.println("============ LABEL ===========");
        System.out.println("============ TYPE ===========");
        for (int i = 1; i <= columnsMetaData.getColumnCount(); i++)
        System.out.println(" ");
        System.out.println("============ TYPE ===========");
        System.out.println("============ TYPENAME ===========");
        for (int i = 1; i <= columnsMetaData.getColumnCount(); i++) {
            System.out.print(columnsMetaData.getColumnTypeName(i)+"\t\t");
        }
        System.out.println(" ");
        System.out.println("============ TYPENAME ===========");
        System.out.println("============ CLASSNAME ===========");
        for (int i = 1; i <= columnsMetaData.getColumnCount(); i++) {
            System.out.print(columnsMetaData.getColumnClassName(i)+"\t\t");
        }
        System.out.println(" ");
        System.out.println("============ CLASSNAME ===========");
        System.out.println("============ DISPLAYSIZE ===========");
        for (int i = 1; i <= columnsMetaData.getColumnCount(); i++) {
            System.out.print(columnsMetaData.getColumnDisplaySize(i)+"\t\t");
        }
        System.out.println(" ");
        System.out.println("============ DISPLAYSIZE ===========");
        System.out.println("============ COLUMNNAME ===========");
        for (int i = 1; i <= columnsMetaData.getColumnCount(); i++) {
            System.out.print(columnsMetaData.getColumnName(i)+"\t\t");
        }
        System.out.println(" ");
        System.out.println("============ COLUMNNAME ===========");
        while (columns.next()) {
            for (int i = 1; i <= columnsMetaData.getColumnCount(); i++) {
                try{
                    if (columnsMetaData.getColumnName(i).equals("TYPE_NAME")){
                        System.out.print(JDBCType.valueOf(columns.getInt("DATA_TYPE"))+"\t\t");
                    }else {
                        System.out.print(columns.getString(columnsMetaData.getColumnName(i))+"\t\t");
                    }
                }catch (IllegalArgumentException e) {
                    System.out.print("=====================");
                    System.out.print(columns.getString(columnsMetaData.getColumnName(i)));
                    System.out.print("=====================");
                }
            }
            System.out.println(" ");
        }
    }

}
