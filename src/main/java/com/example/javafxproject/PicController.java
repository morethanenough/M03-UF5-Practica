package com.example.javafxproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PicController {

    public static String getPic(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m03", "root", "");
            String query = "SELECT * FROM pic WHERE id = ?";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet result = stm.executeQuery();
            if (result.next()) {
                return result.getString("src");
            } else {
                System.out.println("Failed to connect to the database");
            }
            con.close();
        } catch (Exception e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            e.printStackTrace();
        }
        return "";
    }
}
