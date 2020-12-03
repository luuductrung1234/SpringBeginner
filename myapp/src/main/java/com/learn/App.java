package com.learn;

import com.learn.services.HerService;
import com.learn.services.HisService;
import com.learn.services.MyService;
import com.learn.services.YourService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("application-context.xml");

        MyService myService1 = context.getBean(MyService.class);
        MyService myService2 = context.getBean(MyService.class);
        myService1.doSomething();
        myService2.doSomething();

        YourService yourService1 = context.getBean(YourService.class);
        YourService yourService2 = context.getBean(YourService.class);
        yourService1.doSomething();
        yourService2.doSomething();

        context = new ClassPathXmlApplicationContext("application-context.xml");
        HisService hisService = context.getBean(HisService.class);
        hisService.doSomething();
        HerService herService = context.getBean(HerService.class);
        herService.doSomething();
    }
}
