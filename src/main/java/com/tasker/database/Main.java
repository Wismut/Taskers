package com.tasker.database;



public class Main {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        dataBase.createDB();
        dataBase.createTables();
        InputWindow.runWindow();

    }
}


