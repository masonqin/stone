package com.qinxc.jvm;

import com.qinxc.FileStatistic.DataCenter;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

/**
 * @author qxc
 * @date 2019/1/16.
 */
public class ClassLoaderTest {

    DataCenter dataCenter;

    public ClassLoaderTest(DataCenter dataCenter) {
        this.dataCenter = dataCenter;
    }

    public static void main(String[] args) throws Exception {

        new ClassLoaderTest(null).jdbcTest();

    }

    public void jdbcTest() throws Exception {
//        String url = "jdbc:mysql://localhost:3306/cm-storylocker?characterEncoding=UTF-8";
        // 通过java库获取数据库连接
//        Connection conn = java.sql.DriverManager.getConnection(url, "root", "root@555");

        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver driver;
        while (drivers.hasMoreElements()) {
            driver = drivers.nextElement();
            System.out.println(driver.getClass() + "------" + driver.getClass().getClassLoader());
        }
    }

}
