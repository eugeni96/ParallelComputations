package com.company;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	    Manager manager = new Manager(new ArrayList<>());
        try {
            manager.addDownload("1", new URL("http://dev.mysql.com/get/Downloads/Connector-ODBC/5.3/mysql-connector-odbc-5.3.4-win32.msi"),"D:/A1");
            manager.addDownload("2", new URL("http://dev.mysql.com/get/Downloads/Connector-ODBC/5.3/mysql-connector-odbc-5.3.4-winx64.msi"),"D:/A1");
            manager.addDownload("3", new URL("http://dev.mysql.com/get/Downloads/Connector-ODBC/5.3/mysql-connector-odbc-noinstall-5.3.4-win32.zip"),"D:/A1");
            manager.addDownload("4", new URL("http://dev.mysql.com/get/Downloads/Connector-ODBC/5.3/mysql-connector-odbc-noinstall-5.3.4-winx64.zip"),"D:/A1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
