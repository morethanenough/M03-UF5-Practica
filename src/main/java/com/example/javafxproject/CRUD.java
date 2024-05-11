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
            con.close();
        } catch (SQLException e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            e.printStackTrace();
        }

    }

    public ResultSet readUser() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m03","root","");
            String query = "SELECT * FROM user";
            ResultSet result = con.createStatement().executeQuery(query);
            if (result != null) {
                return result;
            } else {
                System.out.println("Failed to connect to the database");
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            e.printStackTrace();
        }
        return null;
    }

    public void deleteUser() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m03","root","");
            String query = "DELETE FROM user WHERE id = ?";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, 1);
            stm.executeUpdate();
            System.out.println("User deleted");
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Delete");
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
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            e.printStackTrace();
        }
    }

    public static ResultSet readGames() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m03","root","");
            String query = "SELECT * FROM game ORDER BY points DESC LIMIT 10";
            ResultSet result = con.createStatement().executeQuery(query);
            if (result != null) {
                return result;
            } else {
                System.out.println("Failed to connect to the database");
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            e.printStackTrace();
        }
        return null;
    }

    public void updateGame(int id, int wins, int loses) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m03","root","");
            String query = "SELECT * FROM game WHERE id = ?";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet result = stm.executeQuery();
            int totalWins = result.getInt("wins") + wins;
            int totalLoses = result.getInt("loses") + loses;
            int totalPoints = (totalWins * 100) - (totalLoses * 50);
            String query2 = "UPDATE game SET wins = ?, loses = ?, points = ? WHERE id = ?";
            PreparedStatement stm2 = con.prepareStatement(query);
            stm2.setInt(1, totalWins);
            stm2.setInt(2, totalLoses);
            stm2.setInt(3, totalPoints);
            stm2.setInt(4, id);
            stm2.executeUpdate();
            System.out.println("Game updated");
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            e.printStackTrace();
        }
    }

    public ResultSet readRivals() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m03","root","");
            String query = "SELECT * FROM rivals";
            ResultSet result = con.createStatement().executeQuery(query);
            if (result != null) {
                return result;
            } else {
                System.out.println("Failed to connect to the database");
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet readPlayerPics() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m03","root","");
            String query = "SELECT * FROM pic WHERE type = 'player'";
            ResultSet result = con.createStatement().executeQuery(query);
            if (result != null) {
                return result;
            } else {
                System.out.println("Failed to connect to the database");
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            e.printStackTrace();
        }
        return null;
    }
}
