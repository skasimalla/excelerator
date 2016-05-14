/*
 * Decompiled with CFR 0_114.
 */
package com.skasimallaATgmail.threads;

import com.skasimallaATgmail.util.ExcelUtil;
import com.skasimallaATgmail.util.PropertiesUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.sql.Connection;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Excelerator {
    static Logger log = Logger.getLogger(Excelerator.class);
    public static Connection conn;
    FileInputStream fileInputStream;

    public static void main(String[] args) {
        String log4jProp = "./conf/logger.properties";
        System.out.println("Hello Welcome to desktop reporting tool, designed and developed by Samuel Kasimalla");
        File fileName = new File(log4jProp);
        if (fileName.exists()) {
            System.out.println("Initializing log4j with: " + log4jProp);
            PropertyConfigurator.configureAndWatch(log4jProp, 60);
        } else {
            System.err.println("*** " + log4jProp + " file not found, so initializing log4j with BasicConfigurator");
            BasicConfigurator.configure();
        }
        log.debug("*********************************Started *****************************************");
        String propFileName = "./conf/excelerator.properties";
        PropertiesUtil.getInstance().loadServiceNowPropertyFile(propFileName);
        System.out.println("Please wait while the reports are being generated");
        System.out.println("Reported Created successfully :" + ExcelUtil.createReport());
        System.out.println("Shutting down application now !!");
    }
}

