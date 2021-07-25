package com.learn.jdbcconsoleapp;

import com.learn.jdbcconsoleapp.dao.PlainSingerDao;
import com.learn.jdbcconsoleapp.dao.SingerDao;
import com.learn.jdbcconsoleapp.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

public class App {
    private static SingerDao singerDao = new PlainSingerDao();
    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        try {
            logger.info("LIST ALL");
            List<Singer> singers = singerDao.findAll();
            for (Singer s : singers) {
                logger.info(s.toString());
            }

            logger.info("-----------------");

            logger.info("INSERT NEW");
            var singer = new Singer();
            singer.setFirstName("Ed");
            singer.setLastName("Sheeran");
            singer.setBirthDate(new Date((new GregorianCalendar(1991, 2, 1991)).getTime().getTime()));
            singerDao.insert(singer);

            logger.info("-----------------");

            logger.info("LIST ALL");
            singers = singerDao.findAll();
            for (Singer s : singers) {
                logger.info(s.toString());
            }

            logger.info("-----------------");

            logger.info("DELETE");
            singerDao.delete(singers.get(singers.size() - 1).getId());

            logger.info("-----------------");

            logger.info("LIST ALL");
            singers = singerDao.findAll();
            for (Singer s : singers) {
                logger.info(s.toString());
            }
        } catch (SQLException ex) {
            logger.error("Something went wrong!", ex);
        }
    }
}
