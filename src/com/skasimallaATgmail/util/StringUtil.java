/*
 * Decompiled with CFR 0_114.
 */
package com.skasimallaATgmail.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class StringUtil {
    static Logger log = Logger.getLogger(StringUtil.class);
    static StringUtil obj = null;

    public static StringUtil getInstance() {
        if (obj == null) {
            obj = new StringUtil();
        }
        return obj;
    }

    public static int safeLength(String str) {
        int length = 0;
        if (str != null) {
            length = str.length();
        }
        return length;
    }

    public static String convertNumbersToWeekDaysandViceVersa(String str) {
        String result = "";
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("1", "SUN");
        hm.put("2", "MON");
        hm.put("3", "TUE");
        hm.put("4", "WED");
        hm.put("5", "THU");
        hm.put("6", "FRI");
        hm.put("7", "SAT");
        hm.put("SUN", "1");
        hm.put("MON", "2");
        hm.put("TUE", "3");
        hm.put("WED", "4");
        hm.put("THU", "5");
        hm.put("FRI", "6");
        hm.put("SAT", "7");
        hm.put("0", "NONE");
        hm.put("NONE", "0");
        String[] parts = str.split(",");
        int i = 0;
        while (i < parts.length) {
            result = String.valueOf(result) + (String)hm.get(parts[i]);
            if (i != parts.length - 1) {
                result = String.valueOf(result) + ",";
            }
            ++i;
        }
        if ("".equals(result)) {
            result = "NONE";
        }
        return result;
    }

    public String getMappingValueFromDB(String category, String columnName, Connection conn) {
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT MAPPED_VALUE FROM fm_ui_mapping where CATEGORY='" + category + "' and COLUMN_NAME='" + columnName + "' ");
            rs.next();
            return rs.getString("MAPPED_VALUE");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String safeStringFromRow(XSSFRow row, int j) {
        String str = "";
        try {
            XSSFCell cell = row.getCell(j);
            str = cell.getCellType() == 1 ? cell.getStringCellValue() : (cell.getCellType() == 0 ? Integer.toString((int)cell.getNumericCellValue()) : "file");
            switch (cell.getCellType()) {
                case 1: {
                    str = cell.getRichStringCellValue().getString();
                    break;
                }
                case 0: {
                    if (DateUtil.isCellDateFormatted(cell)) {
                        str = cell.getDateCellValue().toString();
                        break;
                    }
                    str = Integer.toString((int)cell.getNumericCellValue());
                    break;
                }
                case 4: {
                    str = Boolean.toString(cell.getBooleanCellValue());
                    break;
                }
                case 2: {
                    str = cell.getCellFormula();
                    break;
                }
                case 5: {
                    str = cell.getStringCellValue();
                    break;
                }
                case 3: {
                    str = "";
                    break;
                }
                default: {
                    str = "";
                    break;
                }
            }
        }
        catch (Exception cell) {
            // empty catch block
        }
        return str.trim();
    }
}

