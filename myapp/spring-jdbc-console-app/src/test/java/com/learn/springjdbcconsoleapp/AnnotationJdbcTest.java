package com.learn.springjdbcconsoleapp;

import com.learn.springjdbcconsoleapp.config.AppConfig;
import com.learn.springjdbcconsoleapp.dao.SingerDao;
import com.learn.springjdbcconsoleapp.entities.Album;
import com.learn.springjdbcconsoleapp.entities.Singer;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class AnnotationJdbcTest {
    private GenericApplicationContext ctx;
    private SingerDao singerDao;

    @BeforeEach
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        singerDao = ctx.getBean(SingerDao.class);
        Assertions.assertNotNull(singerDao);
    }

    @AfterEach
    public void tearDown() {
        ctx.close();
    }

    @Test
    public void testFindAll() {
        List<Singer> singers = singerDao.findAll();
        Assertions.assertTrue(!singers.isEmpty());
        listSingers(singers);
        ctx.close();
    }

    @Test
    public void testFindByFirstName() {
        List<Singer> singers = singerDao.findByFirstName("John");
        Assertions.assertTrue(!singers.isEmpty());
        listSingers(singers);
        ctx.close();
    }

    @Test
    public void testSingerUpdate() {
        Singer singer = new Singer();
        singer.setId(1L);
        singer.setFirstName("John Clayton");
        singer.setLastName("Mayer");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1977, 9, 16)).getTime().getTime()));
        singerDao.update(singer);

        List<Singer> singers = singerDao.findAll();
        listSingers(singers);
    }

    @Test
    public void testSingerInsert(){
        Singer singer = new Singer();
        singer.setFirstName("Ed");
        singer.setLastName("Sheeran");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1991, 1, 17)).getTime().getTime()));
        singerDao.insert(singer);

        List<Singer> singers = singerDao.findAll();
        listSingers(singers);
    }

    @Test
    public void testSingerInsertWithAlbum(){
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1940, 8, 16)).getTime().getTime()));

        Album album = new Album();
        album.setTitle("My Kind of Blues");
        album.setReleaseDate(new Date(
                (new GregorianCalendar(1961, 7, 18)).getTime().getTime()));
        singer.addAlbum(album);

        album = new Album();
        album.setTitle("A Heart Full of Blues");
        album.setReleaseDate(new Date(
                (new GregorianCalendar(1962, 3, 20)).getTime().getTime()));
        singer.addAlbum(album);

        singerDao.insertWithAlbum(singer);

        List<Singer> singers = singerDao.findAllWithAlbums();
        listSingers(singers);
    }

    @Test
    public void testFindFirstNameById(){
        String firstName = singerDao.findFirstNameById(2L);
        Assertions.assertEquals("Eric", firstName);
        System.out.println("Retrieved value: " + firstName);
    }


    private void listSingers(List<Singer> singers){
        singers.forEach(singer -> {
            System.out.println(singer);
            if (singer.getAlbums() != null) {
                for (Album album : singer.getAlbums()) {
                    System.out.println("\t--> " + album);
                }
            }
        });
    }
}
