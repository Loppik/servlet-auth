package com.alex.dao;

public class DataStorageModel {
    private static Dao dao = new PostgreSqlDao();

    public static Dao getDao() {
        return dao;
    }
}
