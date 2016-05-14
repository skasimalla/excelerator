/*
 * Decompiled with CFR 0_114.
 */
package com.skasimallaATgmail.util;

import com.skasimallaATgmail.dao.DBConnDAO;
import com.skasimallaATgmail.util.CryptPass;
import com.skasimallaATgmail.util.DBUtil;
import com.skasimallaATgmail.util.ProjectConstants;
import com.skasimallaATgmail.util.PropertiesUtil;
import com.skasimallaATgmail.util.StringUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
    static Logger log = Logger.getLogger(ExcelUtil.class);
    FileInputStream fileInputStream;
    public static ExcelUtil excelutil;

    public static ExcelUtil getInstance() {
        if (excelutil == null) {
            excelutil = new ExcelUtil();
        }
        return excelutil;
    }

    public static XSSFWorkbook writeData(XSSFWorkbook wb) {
        XSSFSheet baseSheet = wb.getSheet(PropertiesUtil.getValueAsString(ProjectConstants.METADATA_SHEET_NAME));
        log.debug("Sheet Name" + baseSheet.getSheetName());
        log.debug("Number of rows:" + baseSheet.getLastRowNum());
        int overAllTabNum = 0;
        int i = 1;
        while (i <= baseSheet.getLastRowNum()) {
            block13 : {
                XSSFRow row = baseSheet.getRow(i);
                log.debug("Row:" + i + " has Number of columns:" + row.getLastCellNum() + " IS_ACTIVE flag is" + StringUtil.getInstance().safeStringFromRow(row, 1));
                if (StringUtil.getInstance().safeStringFromRow(row, 1).equalsIgnoreCase(ProjectConstants.YES_VALUE)) {
                    DBConnDAO dbDetails = ExcelUtil.extractDetailsFromRow(row);
                    if (DBUtil.getInstance().validateDBDAO(dbDetails)) {
                        Connection conn;
                        conn = DBUtil.getInstance().openConnection(dbDetails);
                        try {
                            try {
                                Statement cs = null;
                                log.trace("Queries: " + dbDetails.getQueryList().size());
                                log.trace("Tabs in workbook: " + wb.getNumberOfSheets());
                                int t = 0;
                                while (t < dbDetails.getQueryList().size()) {
                                    XSSFSheet writeSheet = overAllTabNum < wb.getNumberOfSheets() - 1 ? wb.getSheetAt(overAllTabNum + 1) : wb.createSheet();
                                    ++overAllTabNum;
                                    if (!"".equals(dbDetails.getQueryList().get(t))) {
                                        XSSFCell cell;
                                        cs = conn.createStatement();
                                        log.trace("Executing Query:" + dbDetails.getQueryList().get(t));
                                        ResultSet rs = cs.executeQuery(dbDetails.getQueryList().get(t));
                                        ResultSetMetaData rsmd = rs.getMetaData();
                                        int columnsNumber = rsmd.getColumnCount();
                                        int iwrite = 0;
                                        row = writeSheet.createRow(iwrite++);
                                        int j = 0;
                                        while (j < columnsNumber) {
                                            cell = row.createCell(j);
                                            cell.setCellType(1);
                                            cell.setCellValue(rsmd.getColumnName(j + 1));
                                            ++j;
                                        }
                                        while (rs.next()) {
                                            row = writeSheet.createRow(iwrite++);
                                            j = 0;
                                            while (j < columnsNumber) {
                                                cell = row.createCell(j);
                                                cell.setCellType(1);
                                                cell.setCellValue(rs.getString(j + 1));
                                                ++j;
                                            }
                                        }
                                        cs.close();
                                        rs.close();
                                    }
                                    ++t;
                                }
                                log.trace("Successfully completed one inner loop- try statement");
                            }
                            catch (Exception e) {
                                log.error(e);
                                e.printStackTrace();
                                DBUtil.getInstance().closeConn(conn);
                                break block13;
                            }
                        }
                        catch (Throwable var16_17) {
                            DBUtil.getInstance().closeConn(conn);
                            throw var16_17;
                        }
                        DBUtil.getInstance().closeConn(conn);
                    }
                }
            }
            ++i;
        }
        return wb;
    }

    private static DBConnDAO extractDetailsFromRow(XSSFRow row) {
        int j = 1;
        DBConnDAO dbDetails = new DBConnDAO();
        ArrayList<String> queryList = new ArrayList<String>();
        dbDetails.setActiveFlag(StringUtil.getInstance().safeStringFromRow(row, j++));
        dbDetails.setConnName(StringUtil.getInstance().safeStringFromRow(row, j++));
        dbDetails.setDbHostName(StringUtil.getInstance().safeStringFromRow(row, j++));
        dbDetails.setDbPort(StringUtil.getInstance().safeStringFromRow(row, j++));
        dbDetails.setSid(StringUtil.getInstance().safeStringFromRow(row, j++));
        dbDetails.setUserName(StringUtil.getInstance().safeStringFromRow(row, j++));
        dbDetails.setPassword(CryptPass.decrypt(StringUtil.getInstance().safeStringFromRow(row, j++)));
        dbDetails.setOutputReportName(StringUtil.getInstance().safeStringFromRow(row, j++));
        dbDetails.setSkipIntroTab(StringUtil.getInstance().safeStringFromRow(row, j++));
        while (j < row.getLastCellNum()) {
            String query = StringUtil.getInstance().safeStringFromRow(row, j++);
            query = query.replaceAll("--[^\r\n]*", "");
            query = query.replaceAll(ProjectConstants.EOL_REGEX, " ");
            Pattern commentPattern = Pattern.compile("/\\*.*?\\*/", 32);
            query = commentPattern.matcher(query).replaceAll("");
            query = query.replaceAll(";", "");
            queryList.add(query);
        }
        dbDetails.setQueryList(queryList);
        return dbDetails;
    }

    public static boolean createReport() {
        try {
            File dir = new File(PropertiesUtil.getValueAsString(ProjectConstants.INPUT_FOLDER_LOCATION));
            File[] files = dir.listFiles(new FilenameFilter(){

                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(ProjectConstants.XLSX_EXTENSION);
                }
            });
            int ifile = 0;
            while (ifile < files.length) {
                if (files[ifile].isFile()) {
                    log.info("****Working on file " + files[ifile].getName());
                    FileInputStream file = new FileInputStream(String.valueOf(PropertiesUtil.getValueAsString(ProjectConstants.INPUT_FOLDER_LOCATION)) + ProjectConstants.DIR_SEPARATOR + files[ifile].getName());
                    XSSFWorkbook wb = new XSSFWorkbook(file);
                    wb = ExcelUtil.writeData(wb);
                    FileOutputStream fileOut = new FileOutputStream(String.valueOf(PropertiesUtil.getValueAsString(ProjectConstants.OUTPUT_FOLDER_LOCATION)) + ProjectConstants.DIR_SEPARATOR + PropertiesUtil.getValueAsString(ProjectConstants.OUTPUT_FILE_PREFIX) + files[ifile].getName());
                    wb.removeSheetAt(wb.getSheetIndex(PropertiesUtil.getValueAsString(ProjectConstants.METADATA_SHEET_NAME)));
                    wb.write(fileOut);
                    fileOut.flush();
                    fileOut.close();
                }
                ++ifile;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}

