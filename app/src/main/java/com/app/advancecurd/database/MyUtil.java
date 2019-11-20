package com.app.advancecurd.database;

public class MyUtil {
    public static final String DBNAME = "userinfo.db";
    public static final String DBTABLE = "users";
    public static final String CREATE_QUERY = "CREATE TABLE IF NOT EXISTS " + DBTABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT ,fullname varchar(50),username varchar(50) UNIQUE,password varchar(20),branch varchar(10) ,city varchar(30),gender varchar(20),status varchar(20))";
    public final static String keyFileName = "log_file";
    public final static String keyUsernameName = "user_log";
}
