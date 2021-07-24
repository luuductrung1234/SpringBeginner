package com.learn.jdbcconsoleapp.dao;

import com.learn.jdbcconsoleapp.entities.Singer;

import java.sql.SQLException;
import java.util.List;

public interface SingerDao {
    List<Singer> findAll() throws SQLException;

    List<Singer> findByFirstName(String firstName) throws SQLException;

    String findLastNameById(Long id) throws SQLException;

    String findFirstNameById(Long id) throws SQLException;

    void insert(Singer singer) throws SQLException;

    void update(Singer singer) throws SQLException;

    void delete(Long singerId) throws SQLException;

    List<Singer> findAllWithDetail() throws SQLException;

    void insertWithDetail(Singer singer) throws SQLException;
}
