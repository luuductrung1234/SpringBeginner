package com.learn.springjdbcconsoleapp;

import com.learn.springjdbcconsoleapp.config.AppConfig;
import com.learn.springjdbcconsoleapp.dao.SingerDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class JdbcTemplateTest {
    private GenericApplicationContext ctx;
    private SingerDao singerDao;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        singerDao = ctx.getBean(SingerDao.class);
        Assert.assertNotNull(singerDao);
    }

    @After
    public void tearDown() {
        ctx.close();
    }

    @Test
    public void testById() {
        var singer = singerDao.findById(1L);
        Assert.assertTrue(singer.getId() != 0L);
        Assert.assertTrue(!singer.getFirstName().isEmpty());
        Assert.assertTrue(!singer.getLastName().isEmpty());
    }

    @Test
    public void testFindNameById() {
        var name = singerDao.findNameById(2L);
        Assert.assertTrue(!name.isEmpty());
        Assert.assertEquals("Eric Clapton", name);
    }
}
