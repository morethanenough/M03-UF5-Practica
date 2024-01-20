package com.example.javafxproject;

public class DataSingleton {

    private static final DataSingleton instance = new DataSingleton();

    private String userName;

    private DataSingleton(){}

    public static DataSingleton getInstance(){
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
