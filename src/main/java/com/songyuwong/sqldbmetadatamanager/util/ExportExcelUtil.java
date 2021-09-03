package com.songyuwong.sqldbmetadatamanager.util;

import com.songyuwong.sqldbmetadatamanager.exception.ConnectionException;
import com.songyuwong.sqldbmetadatamanager.mysql.Mysql8ConnManager;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;

public class ExportExcelUtil {
    public static void main(String[] args) {
        exportAllMetaDataWithOneExcel();
    }

    public static void exportAllMetaDataWithOneExcel() {
        Mysql8ConnManager mysql8ConnManager = new Mysql8ConnManager();
        Workbook excelFile = new HSSFWorkbook();
        CreationHelper creationHelper = excelFile.getCreationHelper();
        // 超链接单元格样式
        CellStyle linkStyle = excelFile.createCellStyle();
        Font linkFont = excelFile.createFont();
        linkFont.setUnderline(Font.U_SINGLE);
        linkFont.setColor(IndexedColors.BLUE.getIndex());
        linkStyle.setFont(linkFont);
        linkStyle.setAlignment(HorizontalAlignment.CENTER);
        linkStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 文字居中
        CellStyle alignCenter = excelFile.createCellStyle();
        alignCenter.setVerticalAlignment(VerticalAlignment.CENTER);
        alignCenter.setAlignment(HorizontalAlignment.CENTER);
        // 表头样式
        CellStyle headerStyle = excelFile.createCellStyle();
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setFillBackgroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font = excelFile.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setFontHeightInPoints((short) 14);
        headerStyle.setFont(font);
        // 首页
        Sheet indexSheet = excelFile.createSheet("CDA表统计");
        indexSheet.setColumnWidth(0, 30 * 256);
        indexSheet.setColumnWidth(1, 40 * 256);
        // 首页表头
        Row indexRow = indexSheet.createRow(0);
        indexRow.setHeightInPoints(30);
        Cell indexTableHeaderCellName = indexRow.createCell(0);
        indexTableHeaderCellName.setCellValue("CDA表名称");
        indexTableHeaderCellName.setCellStyle(headerStyle);
        Cell indexTableHeaderCellRemarks = indexRow.createCell(1);
        indexTableHeaderCellRemarks.setCellValue("表注释");
        indexTableHeaderCellRemarks.setCellStyle(headerStyle);
        int indexRowCount = 1;
        int cdaTableRowCount;
        try {
            Connection connection = mysql8ConnManager.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(connection.getCatalog(), connection.getCatalog(), "%", new String[]{"TABLE"});
            StringBuilder addressBuilder = new StringBuilder("'");
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                String tableRemarks = tables.getString("REMARKS");
                if (tableName.matches("^hdr_cda\\d{2}$")) {
                    // 写入首页cda单表记录
                    Row row = indexSheet.createRow(indexRowCount++);
                    row.setHeightInPoints(20);
                    Cell cellName = row.createCell(0);
                    Cell cellRemarks = row.createCell(1);
                    cellName.setCellValue(tableName);
                    cellRemarks.setCellValue(tableRemarks);
                    cellRemarks.setCellStyle(alignCenter);
                    // CDA 单表页
                    String safeSheetName = WorkbookUtil.createSafeSheetName(tableName + tableRemarks);
                    addressBuilder.append(safeSheetName).append("'").append("!A1");
                    Sheet sheet = excelFile.createSheet(safeSheetName);
                    sheet.setColumnWidth(1, 256 * 50);
                    sheet.setColumnWidth(2, 256 * 50);
                    // 首页链接到对应的页面
                    Hyperlink hyperlink = creationHelper.createHyperlink(HyperlinkType.DOCUMENT);
                    hyperlink.setAddress(addressBuilder.toString());
                    cellName.setCellStyle(linkStyle);
                    cellName.setHyperlink(hyperlink);
                    addressBuilder.delete(1, addressBuilder.length());
                    // 单表页索引
                    Hyperlink tableToIndexHyperlink = creationHelper.createHyperlink(HyperlinkType.DOCUMENT);
                    tableToIndexHyperlink.setAddress("'CDA表统计'!A1");
                    Row tableToIndexRow = sheet.createRow(0);
                    tableToIndexRow.setHeightInPoints(20);
                    Cell cell = tableToIndexRow.createCell(0);
                    cell.setCellValue("索引表");
                    cell.setCellStyle(linkStyle);
                    cell.setHyperlink(tableToIndexHyperlink);
                    Cell cellHeaderName = tableToIndexRow.createCell(1);
                    cellHeaderName.setCellValue(tableName);
                    cellHeaderName.setCellStyle(alignCenter);
                    Cell cellHeaderRemarks = tableToIndexRow.createCell(2);
                    cellHeaderRemarks.setCellValue(tableRemarks);
                    cellHeaderRemarks.setCellStyle(alignCenter);
                    // 单表页表头
                    cdaTableRowCount = 2;
                    Row cdaTableRowHeader = sheet.createRow(1);
                    cdaTableRowHeader.setHeightInPoints(30);
                    Cell cdaTableRowHeaderCellName = cdaTableRowHeader.createCell(1);
                    cdaTableRowHeaderCellName.setCellValue("字段名");
                    cdaTableRowHeaderCellName.setCellStyle(headerStyle);
                    Cell cdaTableRowHeaderCellRemarks = cdaTableRowHeader.createCell(2);
                    cdaTableRowHeaderCellRemarks.setCellValue("字段注释");
                    cdaTableRowHeaderCellRemarks.setCellStyle(headerStyle);
                    // cda 单表数据
                    ResultSet columns = metaData.getColumns(connection.getCatalog(), connection.getSchema(), tableName, "%");
                    while (columns.next()) {
                        String columnName = columns.getString("COLUMN_NAME");
                        String columnRemarks = columns.getString("REMARKS");
                        Row cdaTableRow = sheet.createRow(cdaTableRowCount++);
                        cdaTableRow.setHeightInPoints(20);
                        Cell cdaTableRowCellName = cdaTableRow.createCell(1);
                        cdaTableRowCellName.setCellValue(columnName);
                        cdaTableRowCellName.setCellStyle(alignCenter);
                        Cell cdaTableRowCellRemarks = cdaTableRow.createCell(2);
                        cdaTableRowCellRemarks.setCellValue(columnRemarks);
                        cdaTableRowCellRemarks.setCellStyle(alignCenter);
                    }
                }
            }
            OutputStream fileOut = new FileOutputStream("C:\\Users\\wsy\\Desktop\\CDA_PDL规范比对.xls");
            excelFile.write(fileOut);
        } catch (ConnectionException | SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                excelFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
