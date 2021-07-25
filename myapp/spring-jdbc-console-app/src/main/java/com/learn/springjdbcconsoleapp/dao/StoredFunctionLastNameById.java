package com.learn.springjdbcconsoleapp.dao;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlFunction;

import javax.sql.DataSource;
import java.sql.Types;

public class StoredFunctionLastNameById extends SqlFunction<String> {
    private static final String SQL = "select getlastnamebyid(?)";

    public StoredFunctionLastNameById(DataSource dataSource) {
        super(dataSource, SQL);
        declareParameter(new SqlParameter(Types.INTEGER));
        compile();
    }
}
