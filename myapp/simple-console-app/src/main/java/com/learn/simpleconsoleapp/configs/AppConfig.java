package com.learn.simpleconsoleapp.configs;

import com.learn.dummybeans.repositories.MyRepositoryImpl;
import com.learn.dummybeans.services.TheirService;
import com.learn.simpleconsoleapp.beans.Singer;
import com.learn.simpleconsoleapp.beans.SingerWithInterface;
import com.learn.simpleconsoleapp.beans.SingerWithJSR250;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@ComponentScan(basePackages = {"com.learn.dummybeans", "com.learn.moneytransfer", "com.learn.simpleconsoleapp"})
@PropertySource({"classpath:application.properties", "classpath:msf.properties"})
@ImportResource({"classpath:application-context.xml"})
@Import(SpringContainerConfig.class)
public class AppConfig {

    @Value("#{systemProperties['java.io.tmpdir']}")
    public String tempDir;

    @Bean
    @Scope("prototype")
    public TheirService getTheirService() {
        return new TheirService(new MyRepositoryImpl());
    }

    /**
     * NOTICE!
     * {@link ResourceBundleMessageSource} must be define as a bean with name "messageSource" to be detected by Container
     * @return
     */
    @Bean("messageSource")
    public ResourceBundleMessageSource getResourceBundleMessageSource() {
        var messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("labels");
        return messageSource;
    }

    /**
     * the CGLIB (bytecode generate), which look into AppConfig (Configuration Bean),
     * then makes the BeanFactory perform initMethod (life-cycle callback) after
     * instantiation (every bean properties was set).
     *
     * @return {@link Singer}
     */
    @Bean(name = {"singerFour", "singer4th", "singer No.4", "s4"}, initMethod = "init", destroyMethod = "destroy")
    @Lazy
    public Singer singerFour() {
        var singer = new Singer();
        singer.setName("TimSell");
        singer.setAge(20);
        singer.setLyricFilePath(tempDir + "singerFour.txt");
        singer.setCreatedBy("Configuration Bean");
        return singer;
    }

    /**
     * the CGLIB (bytecode generate), which look into AppConfig (Configuration Bean),
     * then makes the BeanFactory perform {@link InitializingBean#afterPropertiesSet()}'s method
     * (life-cycle callback) after instantiation (every bean properties was set).
     *
     * @return {@link SingerWithInterface}
     */
    @Bean(name = {"singerWithInterfaceOne", "s.w.i1st", "s.w.i  No.1", "s.w.i1"})
    @Lazy
    public SingerWithInterface singerWithInterfaceOne() {
        var singer = new SingerWithInterface();
        singer.setName("Long");
        singer.setAge(23);
        singer.setLyricFilePath(tempDir + "singerWithInterfaceOne.txt");
        singer.setCreatedBy("Configuration Bean");
        return singer;
    }

    /**
     * When beans are exist. the pre-initialization {@link org.springframework.beans.factory.config.BeanPostProcessor}
     * infrastructure beans are consulted. There is {@link CommonAnnotationBeanPostProcessor} bean will call the
     * method that marked by {@link javax.annotation.PostConstruct} annotation (life-cycle callback)
     * after instantiation (every bean properties was set).
     *
     * @return {@link SingerWithJSR250}
     */
    @Bean(name = {"singerWithJSR250One", "s.w.jsr1st", "s.w.jsr  No.1", "s.w.jsr1"})
    @Lazy
    public SingerWithJSR250 singerWithJSR250One() {
        var singer = new SingerWithJSR250();
        singer.setName("Liu");
        singer.setAge(25);
        singer.setLyricFilePath(tempDir + "singerWithJSR250One.txt");
        singer.setCreatedBy("Configuration Bean");
        return singer;
    }
}
