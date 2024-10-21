package com.lyflexi.multitenant.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description:
 * @Author: lyflexi
 * @project: handson-mulit-tenant
 * @Date: 2024/10/21 15:54
 */
public enum DBDriverEnum {
    MYSQL("MYSQL", "com.mysql.jdbc.Driver"),
    POSTGRESQL("POSTGRESQL", "org.postgresql.Driver"),
    ORACLE("ORACLE", "oracle.jdbc.OracleDriver"),
    ORACLE2("ORACLE", "oracle.jdbc.driver.OracleDriver");

    private String type;
    private String driver;

    public static String getDriver(String type) {
        DBDriverEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            DBDriverEnum driverEnum = var1[var3];
            if (StringUtils.equalsIgnoreCase(driverEnum.getType(), type)) {
                return driverEnum.getDriver();
            }
        }

        throw new IllegalArgumentException("driver enum type is invalid");
    }

    public String getType() {
        return this.type;
    }

    public String getDriver() {
        return this.driver;
    }

    private DBDriverEnum(String type, String driver) {
        this.type = type;
        this.driver = driver;
    }
}
