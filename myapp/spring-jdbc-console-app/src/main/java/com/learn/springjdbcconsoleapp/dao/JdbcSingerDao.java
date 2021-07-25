package com.learn.springjdbcconsoleapp.dao;

import com.learn.springjdbcconsoleapp.entities.Album;
import com.learn.springjdbcconsoleapp.entities.Singer;
import com.learn.springjdbcconsoleapp.seedwords.SingerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcSingerDao implements SingerDao {
    private static Logger logger = LoggerFactory.getLogger(JdbcSingerDao.class);

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SelectAllSingers selectAllSingers;
    private final SelectSingerByFirstName selectSingerByFirstName;
    private final UpdateSinger updateSinger;
    private final InsertSinger insertSinger;
    private final InsertSingerAlbum insertSingerAlbum;
    private final DeleteSinger deleteSinger;
    private final StoredFunctionFirstNameById storedFunctionFirstNameById;
    private final StoredFunctionLastNameById storedFunctionLastNameById;

    @Autowired
    public JdbcSingerDao(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;

        this.selectAllSingers = new SelectAllSingers(dataSource);
        this.selectSingerByFirstName = new SelectSingerByFirstName(dataSource);
        this.updateSinger = new UpdateSinger(dataSource);
        this.insertSinger = new InsertSinger(dataSource);
        this.insertSingerAlbum = new InsertSingerAlbum(dataSource);
        this.storedFunctionFirstNameById = new StoredFunctionFirstNameById(dataSource);
        this.storedFunctionLastNameById = new StoredFunctionLastNameById(dataSource);
        this.deleteSinger = new DeleteSinger(dataSource);
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Singer> findAll() {
        return selectAllSingers.execute();
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", firstName);
        return selectSingerByFirstName.executeByNamedParam(paramMap);
    }

    @Override
    public Singer findById(Long id) {
        String sql = "SELECT ID, FIRST_NAME, LAST_NAME, BIRTH_DATE FROM SINGER WHERE ID= :singerId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("singerId", id);
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, new SingerMapper());
    }

    @Override
    public String findNameById(Long id) {
        String sql = "SELECT CONCAT(FIRST_NAME, ' ', LAST_NAME) FROM SINGER WHERE ID= ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new int[]{Types.INTEGER}, String.class);
    }

    @Override
    public String findLastNameById(Long id) {
        List<String> result = storedFunctionLastNameById.execute(id);
        return result.get(0);
    }

    @Override
    public String findFirstNameById(Long id) {
        List<String> result = storedFunctionFirstNameById.execute(id);
        return result.get(0);
    }

    @Override
    public void insert(Singer singer) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", singer.getFirstName());
        paramMap.put("last_name", singer.getLastName());
        paramMap.put("birth_date", singer.getBirthDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertSinger.updateByNamedParam(paramMap, keyHolder);
        singer.setId(keyHolder.getKey().longValue());
        logger.info("New singer inserted with id: " + singer.getId());
    }

    @Override
    public void insertWithAlbum(Singer singer) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", singer.getFirstName());
        paramMap.put("last_name", singer.getLastName());
        paramMap.put("birth_date", singer.getBirthDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertSinger.updateByNamedParam(paramMap, keyHolder);
        singer.setId(keyHolder.getKey().longValue());
        logger.info("New singer inserted with id: " + singer.getId());
        List<Album> albums =
                singer.getAlbums();
        if (albums != null) {
            for (Album album : albums) {
                paramMap = new HashMap<>();
                paramMap.put("singer_id", singer.getId());
                paramMap.put("title", album.getTitle());
                paramMap.put("release_date", album.getReleaseDate());
                insertSingerAlbum.updateByNamedParam(paramMap);
            }
        }
        insertSingerAlbum.flush();
    }

    @Override
    public List<Singer> findAllWithAlbums() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT s.ID, s.FIRST_NAME, s.LAST_NAME, s.BIRTH_DATE" +
                ", a.ID AS ALBUM_ID, a.TITLE, a.RELEASE_DATE FROM SINGER s " +
                "LEFT JOIN ALBUM a ON s.ID = a.SINGER_ID";
        return jdbcTemplate.query(sql, rs -> {
            Map<Long, Singer> map = new HashMap<>();
            Singer singer;
            while (rs.next()) {
                Long id = rs.getLong("ID");
                singer = map.get(id);
                if (singer == null) {
                    singer = new Singer();
                    singer.setId(id);
                    singer.setFirstName(rs.getString("FIRST_NAME"));
                    singer.setLastName(rs.getString("LAST_NAME"));
                    singer.setBirthDate(rs.getDate("BIRTH_DATE"));
                    singer.setAlbums(new ArrayList<>());
                    map.put(id, singer);
                }
                Long albumId = rs.getLong("ALBUM_ID");
                if (albumId > 0) {
                    Album album = new Album();
                    album.setId(albumId);
                    album.setSingerId(id);
                    album.setTitle(rs.getString("TITLE"));
                    album.setReleaseDate(rs.getDate("RELEASE_DATE"));
                    singer.getAlbums().add(album);
                }
            }
            return new ArrayList<>(map.values());
        });
    }

    @Override
    public void update(Singer singer) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", singer.getFirstName());
        paramMap.put("last_name", singer.getLastName());
        paramMap.put("birth_date", singer.getBirthDate());
        paramMap.put("id", singer.getId());
        updateSinger.updateByNamedParam(paramMap);
        logger.info("Existing singer updated with id: " + singer.getId());
    }

    @Override
    public void delete(Long singerId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", singerId);
        deleteSinger.updateByNamedParam(paramMap);
        logger.info("Deleting singer with id: " + singerId);
    }
}