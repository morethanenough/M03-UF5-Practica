package model;

import com.example.javafxproject.DataSingleton;

import java.sql.*;

public class Game {

    private Integer id_user;
    private String name;
    private Integer id_foto;
    private Integer win_rounds;
    private Integer lost_rounds;
    private Integer points;

    public Game(Integer id_user, String name, Integer id_foto, Integer win_rounds, Integer lost_rounds, Integer points) {
        this.id_user = id_user;
        this.name = name;
        this.id_foto = id_foto;
        this.win_rounds = win_rounds;
        this.lost_rounds = lost_rounds;
        this.points = points;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId_foto() {
        return id_foto;
    }

    public void setId_foto(Integer id_foto) {
        this.id_foto = id_foto;
    }

    public Integer getWin_rounds() {
        return win_rounds;
    }

    public void setWin_rounds(Integer win_rounds) {
        this.win_rounds = win_rounds;
    }

    public Integer getLost_rounds() {
        return lost_rounds;
    }

    public void setLost_rounds(Integer lost_rounds) {
        this.lost_rounds = lost_rounds;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public static int createGame(Integer id_user, String name, Integer id_foto) {
        int win_rounds = 0;
        int lost_rounds = 0;
        int points = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m03","root","");
            String query = "INSERT INTO game (id_user, name, id_foto, win_rounds, lost_rounds, points) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, id_user);
            stm.setString(2, name);
            stm.setInt(3, id_foto);
            stm.setInt(4, win_rounds);
            stm.setInt(5, lost_rounds);
            stm.setInt(6, points);
            stm.executeUpdate();
            int id = getLastId();
            con.close();
            DataSingleton.getInstance().setGameId(id);
            return id;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            e.printStackTrace();
        }
        return 0;
    }

    public static ResultSet getGame(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m03","root","");
            String query = "SELECT * FROM game WHERE id = ?";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet result = stm.executeQuery();
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

    public static int getLastId() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m03","root","");
            String query = "SELECT * FROM game ORDER BY id DESC LIMIT 1";
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                System.out.println("Failed to connect to the database");
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean updateGame (int id, int win_rounds, int lost_rounds) {
        int totalWins = 0;
        int totalLoses = 0;
        int totalPoints = 0;
        try {
            System.out.println("id: " + id + " win_rounds: " + win_rounds + " lost_rounds: " + lost_rounds);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m03","root","");
            //  Recuperem les dades del joc en curs
            String query = "SELECT * FROM game WHERE id = ?";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                totalWins = rs.getInt("win_rounds") + win_rounds;
                totalLoses = rs.getInt("lost_rounds") + lost_rounds;
            }
            totalPoints = (totalWins * 100) - (totalLoses * 50);
            if (totalPoints < 0) {
                totalPoints = 0;
            }
            // Actualitzem les dades del joc en curs
            String query2 = "UPDATE game SET win_rounds = ?, lost_rounds = ?, points = ? WHERE id = ?";
            PreparedStatement stm2 = con.prepareStatement(query2);
            stm2.setInt(1, totalWins);
            stm2.setInt(2, totalLoses);
            stm2.setInt(3, totalPoints);
            stm2.setInt(4, id);
            stm2.executeUpdate();
            con.close();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
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
}
