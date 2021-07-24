package com.learn.springjdbcconsoleapp.seedwords;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import java.sql.SQLException;

public class MySQLErrorCodesTranslator extends SQLErrorCodeSQLExceptionTranslator {
    @Override
    protected DataAccessException customTranslate(String task, String sql, SQLException sqlEx) {
        if(sqlEx.getErrorCode() == -12345){
            return new DeadlockLoserDataAccessException(task, sqlEx);
        }
        return super.customTranslate(task, sql, sqlEx);
    }
}
