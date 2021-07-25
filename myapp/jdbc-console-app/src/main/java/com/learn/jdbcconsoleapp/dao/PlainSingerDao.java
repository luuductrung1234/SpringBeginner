package com.learn.jdbcconsoleapp.dao;

import com.learn.jdbcconsoleapp.entities.Singer;
import com.mysql.cj.jdbc.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlainSingerDao extends BaseDao implements SingerDao {
    private static Logger logger = LoggerFactory.getLogger(PlainSingerDao.class);

    static {
        _tableName = "SINGER";
        _idFieldName = "ID";
        _fieldNames = new ArrayList<>() {{
            add("FIRST_NAME");
            add("LAST_NAME");
            add("BIRTH_DATE");
        }};

        try {
            Class.forName(Driver.class.getName());
        } catch (ClassNotFoundException ex) {
            logger.error("Problem loading DB Driver", ex);
        }
    }

    public PlainSingerDao() {
        super("jdbc:mysql://localhost:3306/MUSICDB?useSSL=true", "root", "P@ssword1");
    }

    @Override
    public List<Singer> findAll() throws SQLException {
        var sql = "SELECT * FROM " + _tableName;

        var singers = new ArrayList<Singer>();

        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql);) {
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                var singer = new Singer();
                singer.setId(resultSet.getLong("id"));
                singer.setFirstName(resultSet.getString("first_name"));
                singer.setLastName(resultSet.getString("last_name"));
                singer.setBirthDate(resultSet.getDate("birth_date"));
                singers.add(singer);
            }
        } catch (SQLException ex) {
            logger.error("Problem when executing SELECT!", ex);
            throw ex;
        }
        return singers;
    }

    @Override
    public List<Singer> findByFirstName(String firstName) throws SQLException {
        return null;
    }

    @Override
    public String findLastNameById(Long id) throws SQLException {
        return null;
    }

    @Override
    public String findFirstNameById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void insert(Singer singer) throws SQLException {
        var sql = "INSERT INTO " + _tableName + sqlInsertFields() + ";";

        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, singer.getFirstName());
            statement.setString(2, singer.getLastName());
            statement.setDate(3, singer.getBirthDate());
            statement.execute();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                singer.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException ex) {
            logger.error("Problem executing INSERT", ex);
            throw ex;
        }
    }

    @Override
    public void update(Singer singer) throws SQLException {

    }

    @Override
    public void delete(Long singerId) throws SQLException {
        var sql = "DELETE FROM " + _tableName + " WHERE " + _idFieldName + " = ?;";

        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, singerId);
            statement.execute();
        } catch (SQLException ex) {
            logger.error("Problem executing DELETE", ex);
            throw ex;
        }
    }

    @Override
    public List<Singer> findAllWithDetail() throws SQLException {
        return null;
    }

    @Override
    public void insertWithDetail(Singer singer) throws SQLException {

    }

}
