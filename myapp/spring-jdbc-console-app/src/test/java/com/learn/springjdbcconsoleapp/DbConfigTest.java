package com.learn.springjdbcconsoleapp;

import com.learn.springjdbcconsoleapp.config.DbConfig;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DbConfigTest {
    private static Logger logger = LoggerFactory.getLogger(DbConfigTest.class);

    @Test
    public void getDataSourceFromXMLContext() throws SQLException {
        var ctx = new GenericXmlApplicationContext();
        //ctx.load("classpath:drivermanager-application-context.xml");
        ctx.load("classpath:dcpc-application-context.xml");
        ctx.refresh();

        var dataSource = ctx.getBean("dataSource", DataSource.class);
        Assert.assertNotNull(dataSource);
        testDataSource(dataSource);

        var embeddedDataSource = ctx.getBean("embeddedDataSource", DataSource.class);
        Assert.assertNotNull(embeddedDataSource);
        testDataSource(embeddedDataSource);

        ctx.close();
    }

    @Test
    public void getDataSourceFromAnnotationContext() throws SQLException {
        var ctx = new AnnotationConfigApplicationContext(DbConfig.class);

        var dataSource = ctx.getBean("dataSource", DataSource.class);
        Assert.assertNotNull(dataSource);
        testDataSource(dataSource);

        var embeddedDataSource = ctx.getBean("embeddedDataSource", DataSource.class);
        Assert.assertNotNull(embeddedDataSource);
        testDataSource(embeddedDataSource);

        ctx.close();
    }

    private void testDataSource(DataSource dataSource) throws SQLException {
        var sql = "SELECT 1";
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(sql)) {
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int mockVal = resultSet.getInt("1");
                Assert.assertTrue(mockVal == 1);
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
    }
}
