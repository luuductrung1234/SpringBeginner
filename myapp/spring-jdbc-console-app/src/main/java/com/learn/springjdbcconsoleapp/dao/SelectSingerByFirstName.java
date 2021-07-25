package com.learn.springjdbcconsoleapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import com.learn.springjdbcconsoleapp.entities.Singer;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class SelectSingerByFirstName extends MappingSqlQuery<Singer> {
    private static final String SQL_FIND_BY_FIRST_NAME = 
        "SELECT ID, FIRST_NAME, LAST_NAME, BIRTH_DATE FROM SINGER WHERE FIRST_NAME = :first_name";

    public SelectSingerByFirstName(DataSource dataSource) {
        super(dataSource, SQL_FIND_BY_FIRST_NAME);
        super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
    }

    @Override
    protected Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Singer singer = new Singer();

        singer.setId(rs.getLong("ID"));
        singer.setFirstName(rs.getString("FIRST_NAME"));
        singer.setLastName(rs.getString("LAST_NAME"));
        singer.setBirthDate(rs.getDate("BIRTH_DATE"));

        return singer;
    }
}
