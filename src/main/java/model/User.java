package model;

import com.example.javafxproject.DataSingleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {

    private String name;
    private String password;
    private boolean admin;

    public User(String name, String password, boolean admin) {
        this.name = name;
        this.password = password;
        this.admin = admin;
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setPassword(String password) { this.password = password; }
    public String getPassword() { return password; }

    public void setAdmin(boolean admin) { this.admin = admin; }
    public boolean getAdmin() { return admin; }

    public String toString() {
        return "User [name=" + name + ", password=" + password + ", admin=" + admin + "]";
    }

    // Mètode per a fer login que retorna l'id de l'usuari o 0 si no existeix o l'usuari o la contrasenya són incorrectes
    public static int login(String name, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m03", "root", "");
            String query = "SELECT * FROM user WHERE name = ? AND pass = ?";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, name);
            stm.setString(2, password);
            ResultSet result = stm.executeQuery();
            if (result.next()) {
                DataSingleton.getInstance().setId_user(result.getInt("id"));
                return result.getInt("id");
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean createUser(String name, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m03", "root", "");
            String query = "INSERT INTO user (name, admin, pass) VALUES (?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, name);
            stm.setString(2, "0");
            stm.setString(3, password);
            stm.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static ResultSet readUser() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m03", "root", "");
            String query = "SELECT * FROM user";
            ResultSet result = con.createStatement().executeQuery(query);
            if (result != null) {
                return result;
            } else {
                System.out.println("Failed to connect to the database");
            }
            con.close();
        } catch (Exception e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteUser(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m03", "root", "");
            String query = "DELETE FROM user WHERE id = ?";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, id);
            stm.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
