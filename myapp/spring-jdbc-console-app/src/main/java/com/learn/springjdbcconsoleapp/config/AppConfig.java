package com.learn.springjdbcconsoleapp.config;

import com.learn.springjdbcconsoleapp.seedwords.MySQLErrorCodesTranslator;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db/jdbc.properties")
@ComponentScan(basePackages = "com.learn.springjdbcconsoleapp")
public class AppConfig {
    private static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${url}")
    private String url;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            return dataSource;
        } catch (Exception e) {
            logger.error("DBCP DataSource bean cannot be created!", e);
            return null;
        }
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        var jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());

        var errorTranslator = new MySQLErrorCodesTranslator();
        errorTranslator.setDataSource(dataSource());
        jdbcTemplate.setExceptionTranslator(errorTranslator);

        return jdbcTemplate;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        var namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource());

        var errorTranslator = new MySQLErrorCodesTranslator();
        errorTranslator.setDataSource(dataSource());
        namedParameterJdbcTemplate.getJdbcTemplate().setExceptionTranslator(errorTranslator);

        return namedParameterJdbcTemplate;
    }
}
