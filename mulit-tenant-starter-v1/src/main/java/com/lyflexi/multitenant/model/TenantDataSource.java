package com.lyflexi.multitenant.model;

/**
 * @Description:
 * @Author: lyflexi
 * @project: handson-mulit-tenant
 * @Date: 2024/10/21 15:59
 */
public class TenantDataSource {
    private String name;
    private String description;
    private String url;
    private String userName;
    private String password;
    private String driver;
    private Integer initPoolSize;
    private Integer maxPoolSize;
    private String tenantId;
    private Integer status;

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getDriver() {
        return this.driver;
    }

    public Integer getInitPoolSize() {
        return this.initPoolSize;
    }

    public Integer getMaxPoolSize() {
        return this.maxPoolSize;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setInitPoolSize(Integer initPoolSize) {
        this.initPoolSize = initPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    protected boolean canEqual(Object other) {
        return other instanceof TenantDataSource;
    }


    public String toString() {
        return "TenantDataSource(name=" + this.getName() + ", description=" + this.getDescription() + ", url=" + this.getUrl() + ", userName=" + this.getUserName() + ", password=" + this.getPassword() + ", driver=" + this.getDriver() + ", initPoolSize=" + this.getInitPoolSize() + ", maxPoolSize=" + this.getMaxPoolSize() + ", tenantId=" + this.getTenantId() + ", status=" + this.getStatus() + ")";
    }
}
