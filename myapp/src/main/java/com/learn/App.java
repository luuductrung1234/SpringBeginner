package com.learn;

import com.learn.configs.AppConfig;
import com.learn.services.HerService;
import com.learn.services.HisService;
import com.learn.services.MyService;
import com.learn.services.YourService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        //RunFirstContext();
        //RunSecondContext();
        RunThirdContext();
    }

    /**
     * Generate ApplicationContext from xml (in file system)
     */
    public static void RunFirstContext() {
        ApplicationContext firstContext = new FileSystemXmlApplicationContext("application-context.xml");

        MyService myService1 = firstContext.getBean(MyService.class);
        MyService myService2 = firstContext.getBean(MyService.class);
        myService1.doSomething();
        myService2.doSomething();

        YourService yourService1 = firstContext.getBean(YourService.class);
        YourService yourService2 = firstContext.getBean(YourService.class);
        yourService1.doSomething();
        yourService2.doSomething();
    }

    /**
     * Generate ApplicationContext from xml (in classpath)
     */
    public static void RunSecondContext() {
        ApplicationContext secondContext = new ClassPathXmlApplicationContext("application-context.xml");

        HisService hisService = secondContext.getBean(HisService.class);
        hisService.doSomething();

        HerService herService = secondContext.getBean(HerService.class);
        herService.doSomething();
    }

    /**
     * Generate ApplicationContext by annotation
     */
    public static void RunThirdContext() {
        ApplicationContext thirdContext = new AnnotationConfigApplicationContext(AppConfig.class);

        HisService hisService = thirdContext.getBean(HisService.class);
        hisService.doSomething();

        HerService herService = thirdContext.getBean(HerService.class);
        herService.doSomething();
    }
}
