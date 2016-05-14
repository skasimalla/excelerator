/*
 * Decompiled with CFR 0_114.
 */
package com.skasimallaATgmail.util;

import com.skasimallaATgmail.dao.DBConnDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class DBUtil {
    Connection conn;
    static Logger log = Logger.getLogger(DBUtil.class);
    public static DBUtil dbutil;

    public static DBUtil getInstance() {
        if (dbutil == null) {
            dbutil = new DBUtil();
        }
        return dbutil;
    }

    public boolean validateDBDAO(DBConnDAO dbDetails) {
        return true;
    }

    public boolean closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                return conn.isClosed();
            }
            catch (SQLException e) {
                log.info("Error occured while trying to close connection");
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean testConn(DBConnDAO dbDetails) {
        Connection testConn = null;
        boolean success = false;
        String oracleDriverType = "jdbc:oracle:thin";
        try {
            testConn = DriverManager.getConnection(String.valueOf(oracleDriverType) + ":@" + dbDetails.getDbHostName() + ":" + dbDetails.getDbPort() + ":" + dbDetails.getSid(), dbDetails.getUserName(), dbDetails.getPassword());
            success = true;
            testConn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return success;
        }
        return success;
    }

    public Connection openConnection(DBConnDAO dbDetails) {
        block8 : {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
            log.debug("Oracle JDBC Driver Registered!");
            try {
                String oracleDriverType = "jdbc:oracle:thin";
                if ("Y".equalsIgnoreCase(dbDetails.getActiveFlag())) {
                    this.conn = DriverManager.getConnection(String.valueOf(oracleDriverType) + ":@" + dbDetails.getDbHostName() + ":" + dbDetails.getDbPort() + ":" + dbDetails.getSid(), dbDetails.getUserName(), dbDetails.getPassword());
                    break block8;
                }
                return null;
            }
            catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
            catch (Exception e1) {
                e1.printStackTrace();
                return null;
            }
        }
        if (this.conn != null) {
            log.info(" JDBC Connectionsuccessful");
        } else {
            log.info("Failed to make connection to :");
        }
        return this.conn;
    }
}

