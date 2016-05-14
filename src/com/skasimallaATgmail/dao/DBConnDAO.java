/*
 * Decompiled with CFR 0_114.
 */
package com.skasimallaATgmail.dao;

import java.util.ArrayList;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class DBConnDAO {
    private String activeFlag;
    private String connName;
    private String dbHostName;
    private String dbPort;
    private String sid;
    private String userName;
    private String password;
    private String outputReportName;
    private String skipIntroTab;
    private ArrayList<String> queryList;

    public String getDbHostName() {
        return this.dbHostName;
    }

    public void setDbHostName(String dbHostName) {
        this.dbHostName = dbHostName;
    }

    public String getDbPort() {
        return this.dbPort;
    }

    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }

    public String getSid() {
        return this.sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getQueryList() {
        return this.queryList;
    }

    public void setQueryList(ArrayList<String> queryList) {
        this.queryList = queryList;
    }

    public String getActiveFlag() {
        return this.activeFlag;
    }

    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getConnName() {
        return this.connName;
    }

    public void setConnName(String connName) {
        this.connName = connName;
    }

    public String getSkipIntroTab() {
        return this.skipIntroTab;
    }

    public void setSkipIntroTab(String skipIntroTab) {
        this.skipIntroTab = skipIntroTab;
    }

    public String getOutputReportName() {
        return this.outputReportName;
    }

    public void setOutputReportName(String outputReportName) {
        this.outputReportName = outputReportName;
    }
}

