package com.learn.springjdbcconsoleapp;

import com.learn.springjdbcconsoleapp.config.AppConfig;
import com.learn.springjdbcconsoleapp.dao.SingerDao;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class JdbcTemplateTest {
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
    public void testById() {
        var singer = singerDao.findById(1L);
        Assertions.assertTrue(singer.getId() != 0L);
        Assertions.assertTrue(!singer.getFirstName().isEmpty());
        Assertions.assertTrue(!singer.getLastName().isEmpty());
    }

    @Test
    public void testFindNameById() {
        var name = singerDao.findNameById(2L);
        Assertions.assertTrue(!name.isEmpty());
        Assertions.assertEquals("Eric Clapton", name);
    }
}
