package com.example.collagenup.Db;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbStrings {


    static final String url = "jdbc:mysql://mn14.webd.pl/iwona08_salon?sessionVariables=character_set_client=utf8,character_set_results=utf8,character_set_connection=utf8,collation_connection=utf8_polish_ci";
    static final String user ="";

    public static String getUrl() {
        return url;
    }

    public static String getUser() {
        return user;
    }

    public static String getPass() {
        return pass;
    }

    public static String getDatabase() {
        return database;
    }

    public static String getJdbcDriver() {
        return JDBC_DRIVER;
    }

    static final String pass ="";
    static final String database="iwona08_salon";
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";


    public DbStrings()
    {
    }

    public static void SetMode() throws ClassNotFoundException, IllegalAccessException, InstantiationException
    {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Class.forName(DbStrings.getJdbcDriver()).newInstance();
    }

    public static  Connection getConnection() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        SetMode();
        return DriverManager.getConnection(
                DbStrings.getUrl(), DbStrings.getUser(), DbStrings.getPass()
        );
    }
}
