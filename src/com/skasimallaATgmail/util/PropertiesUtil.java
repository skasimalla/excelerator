/*
 * Decompiled with CFR 0_114.
 */
package com.skasimallaATgmail.util;

import com.skasimallaATgmail.dao.DBConnDAO;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.FileWatchdog;

public class PropertiesUtil {
    static Logger log = Logger.getLogger(PropertiesUtil.class);
    static Properties serviceNowProp = null;
    static PropertiesWatchdog propertiesWatchdog = null;
    static String snFileName = "";

    public static PropertiesUtil getInstance() {
        return new PropertiesUtil();
    }

    public static DBConnDAO readFromConfig(String choice) {
        DBConnDAO dbDetails = new DBConnDAO();
        dbDetails.setDbHostName(PropertiesUtil.getValueAsString(String.valueOf(choice) + ".DbHostName"));
        dbDetails.setDbPort(PropertiesUtil.getValueAsString(String.valueOf(choice) + ".DbPort"));
        dbDetails.setSid(PropertiesUtil.getValueAsString(String.valueOf(choice) + ".Sid"));
        dbDetails.setUserName(PropertiesUtil.getValueAsString(String.valueOf(choice) + ".UserName"));
        dbDetails.setPassword(PropertiesUtil.getValueAsString(String.valueOf(choice) + ".Password"));
        System.out.println(dbDetails.getPassword());
        return dbDetails;
    }

    public void loadServiceNowPropertyFile(String fileName) {
        log.debug("Loading configuration file:" + fileName);
        try {
            snFileName = fileName;
            if (propertiesWatchdog == null) {
                PropertiesWatchdog propertiesWatchdog = new PropertiesWatchdog(snFileName);
                propertiesWatchdog.setDelay(30000);
                propertiesWatchdog.start();
            }
        }
        catch (Exception e) {
            log.error("Exception in loadServiceNowPropertyFile():", e);
        }
    }

    private void loadProperties() {
        try {
            serviceNowProp = new Properties();
            FileInputStream in = new FileInputStream(snFileName);
            serviceNowProp.load(in);
            System.out.println("Configurations loaded sucessfully");
        }
        catch (Exception e) {
            log.error("Exception in loadProperties():", e);
        }
    }

    public static String getValueAsString(String key) {
        return serviceNowProp.getProperty(key).trim();
    }

    public static Long getValueAsLong(String key) {
        return Long.parseLong(serviceNowProp.getProperty(key).trim());
    }

    public static int getValueAsInt(String key) {
        return Integer.parseInt(serviceNowProp.getProperty(key).trim());
    }

    class PropertiesWatchdog
    extends FileWatchdog {
        PropertiesWatchdog(String fileName) {
            super(fileName);
            PropertiesUtil.log.debug("Initializing PropertiesWatchdog ");
        }

        public void doOnChange() {
            PropertiesUtil.log.info("Start of PropertiesWatchdog doOnChange()");
            try {
                PropertiesUtil.this.loadProperties();
            }
            catch (Exception ex) {
                PropertiesUtil.log.error("Exception found while changes being found in properties file: ", ex);
            }
            PropertiesUtil.log.info("End of doOnChange()");
        }
    }

}

