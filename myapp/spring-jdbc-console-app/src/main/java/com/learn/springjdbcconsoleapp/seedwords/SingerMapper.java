package com.learn.springjdbcconsoleapp.seedwords;

import com.learn.springjdbcconsoleapp.entities.Singer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SingerMapper implements RowMapper<Singer> {

    @Override
    public Singer mapRow(ResultSet rs, int i) throws SQLException {
        Singer singer = new Singer();
        singer.setId(rs.getLong("ID"));
        singer.setFirstName(rs.getString("FIRST_NAME"));
        singer.setLastName(rs.getString("LAST_NAME"));
        singer.setBirthDate(rs.getDate("BIRTH_DATE"));
        return singer;
    }
}
