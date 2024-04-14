package com.example.gym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    static String url = "jdbc:mysql://192.168.1.68:3306/MuscleForge";
    static String user = "usuario1";
    static String password = "contrasenya";

    public static ResultSet connectAndExecuteSQL(String sql) {

        ResultSet databaseResult = null;
        System.out.println("hola");

        try {
            Connection myConnection = DriverManager.getConnection(url, user, password);
            Statement myStatement = myConnection.createStatement();
            databaseResult = myStatement.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println("ERROR in Database Connection: " + e);
        }
        return databaseResult;
    }
}
