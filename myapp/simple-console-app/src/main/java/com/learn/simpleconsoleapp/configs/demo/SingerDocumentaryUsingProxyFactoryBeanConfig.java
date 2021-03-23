package com.learn.simpleconsoleapp.configs.demo;

import com.learn.simpleconsoleapp.beans.SimpleSinger;
import com.learn.simpleconsoleapp.seedworks.advices.ConsoleLoggingAdvice;
import com.learn.simpleconsoleapp.seedworks.advisor.IsModifiedAdvisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.learn.simpleconsoleapp"})
public class SingerDocumentaryUsingProxyFactoryBeanConfig {

    @Value("#{systemProperties['java.io.tmpdir']}")
    public String tempDir;

    @Bean
    public ConsoleLoggingAdvice auditAdvice() {
        return new ConsoleLoggingAdvice();
    }

    @Bean
    public IsModifiedAdvisor mixinAdvisor() {
        return new IsModifiedAdvisor();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public SimpleSinger johnMayer() {
        var singer = new SimpleSinger();
        singer.setName("John Mayer");
        singer.setAge(29);
        singer.setLyricFilePath(tempDir + "singerOne.txt");
        return singer;
    }

    @Bean
    public ProxyFactoryBean singerProxyThree() {
        var proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(johnMayer());
        proxyFactoryBean.setProxyTargetClass(true);
        proxyFactoryBean.addAdvisor(mixinAdvisor());
        return proxyFactoryBean;
    }
}
