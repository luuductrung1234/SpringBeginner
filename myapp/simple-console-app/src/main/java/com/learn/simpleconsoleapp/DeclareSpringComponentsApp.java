package com.learn.simpleconsoleapp;

import com.learn.moneytransfer.commands.CommandManager;
import com.learn.moneytransfer.models.Account;
import com.learn.moneytransfer.models.PaymentHistory;
import com.learn.moneytransfer.models.PaymentMethod;
import com.learn.moneytransfer.repositories.AccountRepository;
import com.learn.moneytransfer.views.PaymentRequest;
import com.learn.simpleconsoleapp.beans.*;
import com.learn.simpleconsoleapp.configs.AppConfig;
import com.learn.simpleconsoleapp.models.Agent;
import com.learn.simpleconsoleapp.seedworks.advices.*;
import com.learn.simpleconsoleapp.seedworks.pointcuts.BetterSingerHighRentDynamicPointcut;
import com.learn.simpleconsoleapp.seedworks.pointcuts.GreatSingerSingingStaticPointcut;
import com.learn.simpleconsoleapp.services.KeyGenerator;
import com.learn.simpleconsoleapp.services.MessageDigester;
import com.learn.simpleconsoleapp.services.MessageRenderer;
import com.learn.simpleconsoleapp.services.SecurityManager;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

public class DeclareSpringComponentsApp {
    public static void main(String[] args) {
        var xmlContext = new GenericXmlApplicationContext();
        xmlContext.load("classpath:application-context.xml");
        xmlContext.refresh();

        var annotationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // ApplicationContext
        {
            System.out.println("\n--------------------------");
            System.out.println("Get Started with ApplicationContext");
            System.out.println("--------------------------\n");

            var messageRenderer = xmlContext.getBean(MessageRenderer.class);
            messageRenderer.render();
        }

        // Bean Naming & Dependency Injection
        {
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
        }

        // Bean Life-Cycle
        {
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
        }

        // Spring FactoryBean
        {
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
        }

        // Internationalization with MessageSource
        {
            System.out.println("\n--------------------------");
            System.out.println("Internationalization with MessageSource");
            System.out.println("--------------------------\n");

            System.out.println("msg (en): " + annotationContext.getMessage("msg", null, Locale.ENGLISH));
            System.out.println("msg (de-DE): " + annotationContext.getMessage("msg", null, Locale.GERMANY));

            System.out.println("nameMsg (en): " + annotationContext.getMessage("nameMsg", new Object[]{"John", "Mayer"}, Locale.ENGLISH));
            System.out.println("nameMsg (de-DE): " + annotationContext.getMessage("nameMsg", new Object[]{"John", "Mayer"}, Locale.GERMANY));
        }

        // ApplicationEvent
        {
            System.out.println("\n--------------------------");
            System.out.println("ApplicationEvent");
            System.out.println("--------------------------\n");

            CommandManager commandManager = annotationContext.getBean(CommandManager.class);
            AccountRepository accountRepository = annotationContext.getBean(AccountRepository.class);
            Account account = accountRepository.getById(1).get();
            PaymentHistory result = (PaymentHistory) commandManager.process(new PaymentRequest(1, 400_000, PaymentMethod.EWallet));
        }

        // Resources
        {
            System.out.println("\n--------------------------");
            System.out.println("Resources");
            System.out.println("--------------------------\n");


            try {
                var file = Files.createTempFile("test", "txt").toFile();
                file.deleteOnExit();
                Resource res1 = annotationContext.getResource("file://" + file.getPath());
                System.out.println(res1.getClass() + "\n\t" + res1.getURL().getContent());
                Resource res2 = annotationContext.getResource("classpath:dummy.txt");
                System.out.println(res2.getClass() + "\n\t" + res2.getURL().getContent());
                Resource res3 = annotationContext.getResource("http://www.google.com");
                System.out.println(res3.getClass() + "\n\t" + res3.getURL().getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Environment and PropertySource Abstraction
        {
            System.out.println("\n--------------------------");
            System.out.println("Environment and PropertySource Abstraction");
            System.out.println("--------------------------\n");

            var env = annotationContext.getEnvironment();
            var propertySources = env.getPropertySources();

            var appMap = new HashMap<String, Object>();
            appMap.put("user.home", "application_home");
            propertySources.addLast(new MapPropertySource("spring5_custom_MAP", appMap));

            System.out.println("user.home: " + System.getProperty("user.home"));
            System.out.println("JAVA_HOME: " + System.getProperty("JAVA_HOME"));

            System.out.println("user.home: " + env.getProperty("user.home"));
            System.out.println("JAVA_HOME: " + env.getProperty("JAVA_HOME"));

            propertySources.addFirst(new MapPropertySource("spring5_custom_MAP", appMap));

            System.out.println("user.home: " + System.getProperty("user.home"));
            System.out.println("JAVA_HOME: " + System.getProperty("JAVA_HOME"));

            System.out.println("user.home: " + env.getProperty("user.home"));
            System.out.println("JAVA_HOME: " + env.getProperty("JAVA_HOME"));
        }

        // Validation, Data Binding, and Type Conversion
        {
            System.out.println("\n--------------------------");
            System.out.println("Bean Wrapper");
            System.out.println("--------------------------\n");

            // BeanWrapperImpl implements BeanWrapper interface
            // and extends PropertyEditorRegistrySupport, which implements PropertyEditorRegistry interface

            // DataBinder also implements PropertyEditorRegistry interface

            var companyBeanWrapper = new BeanWrapperImpl(new Company());
            companyBeanWrapper.setPropertyValue("name", "Spring Pivotal Inc.");
            companyBeanWrapper.setPropertyValue(new PropertyValue("branch", "San Jose st."));

            var tomBeanWrapper = new BeanWrapperImpl(new Employee());
            tomBeanWrapper.setPropertyValue("name", "Tom Davies");
            tomBeanWrapper.setPropertyValue("salary", 1500);

            companyBeanWrapper.setPropertyValue("managingDirector", tomBeanWrapper.getWrappedInstance());

            var managingDirectorSalary = (Float) companyBeanWrapper.getPropertyValue("managingDirector.salary");
            System.out.println("Managing Director salary: " + managingDirectorSalary);


            System.out.println("\n--------------------------");
            System.out.println("JavaBean PropertyEditor");
            System.out.println("--------------------------\n");

            // Spring uses the concept of a PropertyEditor to effect the conversion
            // between an Object and a String. It can be handy to represent properties
            // in a different way than the object itself

            // use PropertyEditorRegistrar, which receive PropertyEditorRegistry, to call
            // PropertyEditorRegistry.registerCustomEditor() to register custom PropertyEditor

            var samplePropertyEditorBean = annotationContext.getBean(SamplePropertyEditorBean.class);
            System.out.println("Constructed bean: " + samplePropertyEditorBean);

            var customerBean = annotationContext.getBean(Customer.class);
            System.out.println("Customer full name: " + customerBean.getFullName());


            System.out.println("\n--------------------------");
            System.out.println("Spring Type Conversion");
            System.out.println("--------------------------\n");

            // Spring 3 introduce core.convert package that provides a general type conversion system.

            // The system defines an SPI to implement type conversion logic. Within a Spring container,
            // you can use this system as an alternative to PropertyEditor implementation to convert
            // externalized bean property value strings to the required property types

            // The system defines an API to perform type conversions at runtime. You can use the
            // public API anywhere in your application where type conversion is needed.


            System.out.println("\n--------------------------");
            System.out.println("Spring Field Formatting");
            System.out.println("--------------------------\n");

            // Type conversion requirement of a typical client environment (e.g web or desktop local environment)

            // TODO: read later


            System.out.println("\n--------------------------");
            System.out.println("Java Bean Validation");
            System.out.println("--------------------------\n");

            // https://beanvalidation.org/

            // TODO: read later
        }

        // Spring AOP
        {
            {
                System.out.println("\n--------------------------");
                System.out.println("Spring AOP - MethodInterceptor & ProxyFactory");
                System.out.println("--------------------------\n");

                var targetAgent = new Agent();

                var proxyFactory = new ProxyFactory();
                proxyFactory.addAdvice(new AgentDecorator()); // ProxyFactory will wrap the given Advice with DefaultPointcutAdvisor
                proxyFactory.setTarget(targetAgent);
                var proxiedAgent = (Agent) proxyFactory.getProxy();

                System.out.println("Normal agent:");
                targetAgent.speak();
                System.out.println();

                System.out.println("Proxied agent:");
                proxiedAgent.speak();
                System.out.println();
            }

            {
                System.out.println("\n--------------------------");
                System.out.println("Spring AOP - BeforeAdvice");
                System.out.println("--------------------------\n");

                var targetAgent = new Agent();

                var proxyFactory = new ProxyFactory();
                proxyFactory.addAdvice(new SimpleBeforeAdvice()); // ProxyFactory will wrap the given Advice with DefaultPointcutAdvisor
                proxyFactory.setTarget(targetAgent);

                var proxiedAgent = (Agent) proxyFactory.getProxy();

                System.out.println("Proxied agent:");
                proxiedAgent.speak();
                System.out.println();
                proxiedAgent.shoot(); // the DefaultPointcutAdvisor will apply advice to all methods of advised object
                System.out.println();

                var secretBean = new SecretBean();

                proxyFactory = new ProxyFactory();
                proxyFactory.addAdvice(new SecurityAdvice()); // ProxyFactory will wrap the given Advice with DefaultPointcutAdvisor
                proxyFactory.setTarget(secretBean);

                var proxiedSecretBean = (SecretBean) proxyFactory.getProxy();

                var securityManager = new SecurityManager();

                securityManager.login("John", "pwd");
                proxiedSecretBean.writeSecretMessage();
                securityManager.logout();

                try {
                    proxiedSecretBean.writeSecretMessage();
                } catch (SecurityException ex) {
                    System.out.println(ex.getMessage());
                }
            }

            {
                System.out.println("\n--------------------------");
                System.out.println("Spring AOP - AfterAdvice");
                System.out.println("--------------------------\n");

                var keyGenerator = new KeyGenerator();

                var proxyFactory = new ProxyFactory();
                proxyFactory.addAdvice(new SimpleAfterReturningAdvice()); // ProxyFactory will wrap the given Advice with DefaultPointcutAdvisor
                proxyFactory.addAdvice(new WeakKeyCheckAdvice()); // ProxyFactory will wrap the given Advice with DefaultPointcutAdvisor
                proxyFactory.setTarget(keyGenerator);

                var proxiedKeyGenerator = (KeyGenerator) proxyFactory.getProxy();

                try {
                    var key = proxiedKeyGenerator.getKey();
                    System.out.println("Generated Key: " + key);
                } catch (SecurityException ex) {
                    System.out.println(ex.getMessage());
                }
            }

            {
                System.out.println("\n--------------------------");
                System.out.println("Spring AOP - AroundAdvice");
                System.out.println("--------------------------\n");

                var targetAgent = new Agent();

                var proxyFactory = new ProxyFactory();
                proxyFactory.addAdvice(new AgentProfilingInterceptor()); // ProxyFactory will wrap the given Advice with DefaultPointcutAdvisor
                proxyFactory.setTarget(targetAgent);
                var proxiedAgent = (Agent) proxyFactory.getProxy();

                proxiedAgent.doingMission();
            }

            {
                System.out.println("\n--------------------------");
                System.out.println("Spring AOP - Static Pointcut - StaticMethodMatcherPointcut");
                System.out.println("--------------------------\n");

                // Spring Advisor is a representation of Aspect
                // Spring Advisor is a coupling of Advice and Pointcuts
                var customAdvisor = new DefaultPointcutAdvisor(
                        new GreatSingerSingingStaticPointcut(), new ConsoleLoggingAdvice());

                var betterSinger = new BetterSinger();
                var proxyFactory = new ProxyFactory();
                proxyFactory.addAdvisor(customAdvisor);
                proxyFactory.setTarget(betterSinger);
                var proxiedBetterSinger = (BetterSinger) proxyFactory.getProxy();

                var greatSinger = new GreatSinger();
                proxyFactory = new ProxyFactory();
                proxyFactory.addAdvisor(customAdvisor);
                proxyFactory.setTarget(greatSinger);
                var proxiedGreatSinger = (GreatSinger) proxyFactory.getProxy();

                proxiedBetterSinger.sing();
                proxiedGreatSinger.sing();
                System.out.println();

                for (var entry : GreatSingerSingingStaticPointcut.METHOD_CHECKING_COUNT.entrySet()) {
                    System.out.println("Pointcut method checking process perform:" +
                            "\n\t - on: " + entry.getKey() +
                            "\n\t - with: " + entry.getValue() + " (times)");
                }

                // TODO: Why "sing()" checking 2 times?
                // answer: one during the initial phase when all methods are checked
                // and another when it is first invoked.
            }

            {
                System.out.println("\n--------------------------");
                System.out.println("Spring AOP - Dynamic Pointcut - DynamicMethodMatcherPointcut");
                System.out.println("--------------------------\n");

                // Spring Advisor is a representation of Aspect
                // Spring Advisor is a coupling of Advice and Pointcuts
                var customAdvisor = new DefaultPointcutAdvisor(
                        new BetterSingerHighRentDynamicPointcut(), new ConsoleLoggingAdvice());

                var betterSinger = new BetterSinger();
                var proxyFactory = new ProxyFactory();
                proxyFactory.addAdvisor(customAdvisor);
                proxyFactory.setTarget(betterSinger);
                var proxiedBetterSinger = (BetterSinger) proxyFactory.getProxy();

                var greatSinger = new GreatSinger();
                proxyFactory = new ProxyFactory();
                proxyFactory.addAdvisor(customAdvisor);
                proxyFactory.setTarget(greatSinger);
                var proxiedGreatSinger = (GreatSinger) proxyFactory.getProxy();

                proxiedBetterSinger.rent(800);
                proxiedGreatSinger.rent(2000);
                proxiedBetterSinger.rent(1200);
                proxiedBetterSinger.rent(780);
                proxiedBetterSinger.rent(2080);

                System.out.println();

                for (var entry : BetterSingerHighRentDynamicPointcut.METHOD_STATIC_CHECKING_COUNT.entrySet()) {
                    System.out.println("Pointcut method static-checking process perform:" +
                            "\n\t - on: " + entry.getKey() +
                            "\n\t - with: " + entry.getValue() + " (times)");
                }

                System.out.println();

                for (var entry : BetterSingerHighRentDynamicPointcut.METHOD_DYNAMIC_CHECKING_COUNT.entrySet()) {
                    System.out.println("Pointcut method dynamic-checking process perform:" +
                            "\n\t - on: " + entry.getKey() +
                            "\n\t - with: " + entry.getValue() + " (times)");
                }

                // because of static-check was overridden, static-check for "rent()" occur 2 times
                // and dynamic-check occur 4 times
            }

            {
                System.out.println("\n--------------------------");
                System.out.println("Spring AOP - Static Pointcut - JdkRegexMethodPointcut");
                System.out.println("--------------------------\n");

                var singer = new BetterSinger();

                var jdkRegexPointcut = new JdkRegexpMethodPointcut();
                jdkRegexPointcut.setPattern(".*sing.*");

                var proxyFactory = new ProxyFactory();
                proxyFactory.addAdvisor(new DefaultPointcutAdvisor(jdkRegexPointcut, new ConsoleLoggingAdvice()));
                proxyFactory.setTarget(singer);

                var proxiedSinger = (BetterSinger) proxyFactory.getProxy();

                proxiedSinger.rent(1000);
                proxiedSinger.sing();
            }
        }


        // -> When application reach this point, ApplicationContext will perform destroy() or shutdown() automatically
    }
}
