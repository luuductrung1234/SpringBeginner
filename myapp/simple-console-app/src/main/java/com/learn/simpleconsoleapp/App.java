package com.learn.simpleconsoleapp;

import com.learn.dummybeans.services.*;
import com.learn.moneytransfer.commands.CommandManager;
import com.learn.moneytransfer.models.Account;
import com.learn.moneytransfer.models.PaymentHistory;
import com.learn.moneytransfer.models.PaymentMethod;
import com.learn.moneytransfer.repositories.AccountRepository;
import com.learn.moneytransfer.views.PaymentRequest;
import com.learn.simpleconsoleapp.configs.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "local");

//        FileSystemXmlContextDemo();
//        ClassPathXmlContextDemo();
//        AnnotationContextDemo();
//        MoneyTransferDemo();
    }

    /**
     * Generate ApplicationContext from xml (in file system)
     */
    public static void FileSystemXmlContextDemo() {
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
    public static void ClassPathXmlContextDemo() {
        ApplicationContext secondContext = new ClassPathXmlApplicationContext("application-context.xml");

        Service myService = secondContext.getBean(MyService.class);
        myService.doSomething();

        Service yourService = secondContext.getBean(YourService.class);
        yourService.doSomething();

        Service hisService = secondContext.getBean(HisService.class);
        hisService.doSomething();

        Service herService = secondContext.getBean(HerService.class);
        herService.doSomething();
    }

    /**
     * Generate ApplicationContext by annotation
     */
    public static void AnnotationContextDemo() {
        ApplicationContext thirdContext = new AnnotationConfigApplicationContext(AppConfig.class);

        Service service = thirdContext.getBean(HisService.class);
        service.doSomething();

        Service herService = thirdContext.getBean(HerService.class);
        herService.doSomething();

        Service theirService = thirdContext.getBean(TheirService.class);
        theirService.doSomething();
    }

    /**
     * Apply IoC Container knowledge to build simple CQRS Money Transfer Demo
     * (Spring Framework docs - IoC Container section)
     */
    public static void MoneyTransferDemo() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println("Business Demo");
        System.out.println("-----------------------------------");
        System.out.println();

        CommandManager commandManager = context.getBean(CommandManager.class);
        AccountRepository accountRepository = context.getBean(AccountRepository.class);

        Account account = accountRepository.getById(1).get();
        System.out.println("Account before payment: \n" + account + "\n");

        PaymentHistory result = (PaymentHistory) commandManager.process(new PaymentRequest(1, 400_000, PaymentMethod.EWallet));
        System.out.println("Result from command: \n" + result + "\n");

        System.out.println("Account after payment: \n" + account + "\n");

        result = (PaymentHistory) commandManager.process(new PaymentRequest(1, 3_900_000, PaymentMethod.Bank));
        System.out.println("Result from command: \n" + result + "\n");

        System.out.println("Account after payment: \n" + account + "\n");
    }
}
