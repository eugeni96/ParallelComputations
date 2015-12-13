package com.company;

import java.io.IOException;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
	    Manager manager = new Manager();
        try {
            //manager.addDownload(new URL("http://dev.mysql.com/get/Downloads/Connector-ODBC/5.3/mysql-connector-odbc-5.3.4-win32.msi"),"E:/A1", 0);
            //manager.addDownload(new URL("http://dev.mysql.com/get/Downloads/Connector-ODBC/5.3/mysql-connector-odbc-5.3.4-winx64.msi"),"E:/A1", 0);
            //manager.addDownload(new URL("http://dev.mysql.com/get/Downloads/Connector-ODBC/5.3/mysql-connector-odbc-noinstall-5.3.4-win32.zip"),"E:/A1", 0);
            //manager.addDownload(new URL("http://dev.mysql.com/get/Downloads/Connector-ODBC/5.3/mysql-connector-odbc-noinstall-5.3.4-winx64.zip"),"E:/A1", 0);
            manager.addDownload(new URL("http://sheldonbrown.com/index.html"),"E:/A1",1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
