package com.learn.simpleconsoleapp;

import com.learn.moneytransfer.commands.CommandManager;
import com.learn.moneytransfer.models.Account;
import com.learn.moneytransfer.models.PaymentHistory;
import com.learn.moneytransfer.models.PaymentMethod;
import com.learn.moneytransfer.repositories.AccountRepository;
import com.learn.moneytransfer.views.PaymentRequest;
import com.learn.simpleconsoleapp.beans.*;
import com.learn.simpleconsoleapp.configs.AppConfig;
import com.learn.simpleconsoleapp.services.MessageDigester;
import com.learn.simpleconsoleapp.services.MessageRenderer;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Locale;

public class DeclareSpringComponentsApp {
    public static void main(String[] args) {
        var xmlContext = new GenericXmlApplicationContext();
        xmlContext.load("classpath:application-context.xml");
        xmlContext.refresh();

        var annotationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("\n--------------------------");
        System.out.println("Get Started with ApplicationContext");
        System.out.println("--------------------------\n");

        var messageRenderer = xmlContext.getBean(MessageRenderer.class);
        messageRenderer.render();


        System.out.println("\n--------------------------");
        System.out.println("Collection Injection");
        System.out.println("--------------------------\n");

        var collectionInjection = xmlContext.getBean(CollectionInjection.class);
        collectionInjection.displayInfo();


        System.out.println("\n--------------------------");
        System.out.println("Method Injection");
        System.out.println("--------------------------\n");

        // TODO: read later


        System.out.println("\n--------------------------");
        System.out.println("Understand Bean Naming");
        System.out.println("--------------------------\n");

        var stringBeans = xmlContext.getBeansOfType(String.class);

        stringBeans.entrySet().stream().forEach(b -> System.out.println(b.getKey()));

        stringBeans.entrySet().stream().forEach(b ->
        {
            System.out.println("[id]: " + b.getKey()
                    + "\n\t[aliases]: "
                    + Arrays.toString(xmlContext.getAliases(b.getKey())) + "\n");
        });


        System.out.println("\n--------------------------");
        System.out.println("Bean Life-Cycle");
        System.out.println("--------------------------\n");

        Arrays.stream(annotationContext.getBeanFactory().getBeanNamesForType(Singer.class)).forEach(beanName -> {
            try {
                var singer = annotationContext.getBean(beanName);

                System.out.println("[id]: " + beanName
                        + "\n\t[aliases]: "
                        + Arrays.toString(annotationContext.getAliases(beanName))
                        + "\n\t[details]: " + singer + "\n");
            } catch (BeanCreationException ex) {
                System.out.println("An error occurred in bean configuration: " + ex.getMessage() + "\n");
            }
        });

        Arrays.stream(annotationContext.getBeanFactory().getBeanNamesForType(SingerWithInterface.class)).forEach(beanName -> {
            try {
                var singer = annotationContext.getBean(beanName);

                System.out.println("[id]: " + beanName
                        + "\n\t[aliases]: "
                        + Arrays.toString(annotationContext.getAliases(beanName))
                        + "\n\t[details]: " + singer + "\n");
            } catch (BeanCreationException ex) {
                System.out.println("An error occurred in bean configuration: " + ex.getMessage() + "\n");
            }
        });

        Arrays.stream(annotationContext.getBeanFactory().getBeanNamesForType(SingerWithJSR250.class)).forEach(beanName -> {
            try {
                var singer = annotationContext.getBean(beanName);

                System.out.println("[id]: " + beanName
                        + "\n\t[aliases]: "
                        + Arrays.toString(annotationContext.getAliases(beanName))
                        + "\n\t[details]: " + singer + "\n");
            } catch (BeanCreationException ex) {
                System.out.println("An error occurred in bean configuration: " + ex.getMessage() + "\n");
            }
        });


        System.out.println("\n--------------------------");
        System.out.println("Spring FactoryBean");
        System.out.println("--------------------------\n");

        var digester = annotationContext.getBean(MessageDigester.class);
        digester.digest("Hello World!");

        var messageDigestBeanName = Arrays.stream(annotationContext.getBeanNamesForType(MessageDigest.class)).findFirst();
        if (messageDigestBeanName.isPresent()) {
            var messageDigestFactoryBean = annotationContext.getBean("&" + messageDigestBeanName.get());
            System.out.println("MessageDigestFactoryBean: " + messageDigestFactoryBean + "\n");
        }


        System.out.println("\n--------------------------");
        System.out.println("JavaBean PropertyEditor");
        System.out.println("--------------------------\n");

        var samplePropertyEditorBean = annotationContext.getBean(SamplePropertyEditorBean.class);

        var customerBean = annotationContext.getBean(Customer.class);
        System.out.println(customerBean.getFullName());


        System.out.println("\n--------------------------");
        System.out.println("Internationalization with MessageSource");
        System.out.println("--------------------------\n");

        System.out.println("msg (en): " + annotationContext.getMessage("msg", null, Locale.ENGLISH));
        System.out.println("msg (de-DE): " + annotationContext.getMessage("msg", null, Locale.GERMANY));

        System.out.println("nameMsg (en): " + annotationContext.getMessage("nameMsg", new Object[]{"John", "Mayer"}, Locale.ENGLISH));
        System.out.println("nameMsg (de-DE): " + annotationContext.getMessage("nameMsg", new Object[]{"John", "Mayer"}, Locale.GERMANY));


        System.out.println("\n--------------------------");
        System.out.println("ApplicationEvent");
        System.out.println("--------------------------\n");

        CommandManager commandManager = annotationContext.getBean(CommandManager.class);
        AccountRepository accountRepository = annotationContext.getBean(AccountRepository.class);
        Account account = accountRepository.getById(1).get();
        PaymentHistory result = (PaymentHistory) commandManager.process(new PaymentRequest(1, 400_000, PaymentMethod.EWallet));


        System.out.println("\n--------------------------");
        System.out.println("Resources");
        System.out.println("--------------------------\n");

        try{
            var file = Files.createTempFile("test", "txt").toFile();
            file.deleteOnExit();
            Resource res1 = annotationContext.getResource("file://" + file.getPath());
            System.out.println(res1.getClass() + "\n\t" + res1.getURL().getContent());
            Resource res2 = annotationContext.getResource("classpath:dummy.txt");
            System.out.println(res2.getClass() + "\n\t" + res2.getURL().getContent());
            Resource res3 = annotationContext.getResource("http://www.google.com");
            System.out.println(res3.getClass() + "\n\t" + res3.getURL().getContent());
        }catch (IOException e){
            e.printStackTrace();
        }


        // -> When application reach this point, ApplicationContext will perform destroy() or shutdown() automatically
    }
}
