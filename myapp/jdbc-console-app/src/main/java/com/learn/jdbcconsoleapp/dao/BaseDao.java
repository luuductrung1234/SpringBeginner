package com.learn.jdbcconsoleapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao {
    protected static String _tableName;
    protected static String _idFieldName;
    protected static List<String> _fieldNames = new ArrayList<>();

    protected String _connectionString;
    protected String _user;
    protected String _password;

    public BaseDao(String connectionString, String user, String password) {
        _connectionString = connectionString;
        _user = user;
        _password = password;
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(_connectionString, _user, _password);
    }

    protected String sqlInsertFields() {
        var fieldsBuilder = new StringBuilder();
        var placeHolderBuilder = new StringBuilder();
        fieldsBuilder.append("(");
        placeHolderBuilder.append("(");
        for (var field : _fieldNames) {
            fieldsBuilder.append(field + ",");
            placeHolderBuilder.append("?,");
        }
        var lastIndex = fieldsBuilder.length() - 1;
        fieldsBuilder.replace(fieldsBuilder.length() - 1, fieldsBuilder.length(), ")");
        placeHolderBuilder.replace(placeHolderBuilder.length() - 1, placeHolderBuilder.length(), ")");
        return fieldsBuilder.toString() + " VALUES " + placeHolderBuilder.toString();
    }
}
