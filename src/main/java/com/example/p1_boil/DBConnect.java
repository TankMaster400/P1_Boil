package com.example.p1_boil;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

    public Connection databaselink;

    public Connection getDB()
    {
        String url = "jdbc:sqlite:Data.sqlite";

        try{
            databaselink = DriverManager.getConnection(url);
            System.out.println("Connected");
        }
        catch(Exception e){
            System.out.println(e);
        }

        return databaselink;
    }
}
