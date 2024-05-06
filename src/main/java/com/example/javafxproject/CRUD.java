package com.example.javafxproject;


import java.sql.*;

public class CRUD {
    public void createUser(String name, String password) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m03","root","");
            String query = "INSERT INTO user (name, admin, password) VALUES (?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, name);
            stm.setString(2, "0");
            stm.setString(3, password);
            stm.executeUpdate();
            System.out.println("User created");
        } catch (SQLException e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            e.printStackTrace();
        }

    }

    public void createGame(Integer id_user, String name, Integer id_foto) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m03","root","");
            String query = "INSERT INTO game (id_user, name, id_foto) VALUES (?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, id_user);
            stm.setString(2, name);
            stm.setInt(3, id_foto);
            stm.executeUpdate();
            System.out.println("Game created");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            e.printStackTrace();
        }
    }

    public ResultSet readGame() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m03","root","");
            String query = "SELECT * FROM game ORDER BY score DESC LIMIT 10";
            ResultSet result = con.createStatement().executeQuery(query);
            if (result != null) {
                return result;
            } else {
                System.out.println("Failed to connect to the database");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            e.printStackTrace();
        }
        return null;
    }

    public void updateGame(int id, int wins, int loses) {

    }

    public void deleteUser() {
        System.out.println("Delete");
    }
}
