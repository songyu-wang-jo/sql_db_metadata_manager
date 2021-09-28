package com.songyuwong.sqldbmetadatamanager.model;

public class OracleConnectionBaseInfo {

    private String driver;

    private String user;

    private String password;

    private String url;

    private Boolean remarksReporting;

    public OracleConnectionBaseInfo() {
    }

    public OracleConnectionBaseInfo(String driver, String user, String password, String url, Boolean remarksReporting) {
        this.driver = driver;
        this.user = user;
        this.password = password;
        this.url = url;
        this.remarksReporting = remarksReporting;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getRemarksReporting() {
        return remarksReporting;
    }

    public void setRemarksReporting(Boolean remarksReporting) {
        this.remarksReporting = remarksReporting;
    }

    @Override
    public String toString() {
        return "OracleConnectionBaseInfo{" +
                "driver='" + driver + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", url='" + url + '\'' +
                ", remarksReporting=" + remarksReporting +
                '}';
    }
}
