package com.learn.simpleconsoleapp;

import com.learn.simpleconsoleapp.beans.*;
import com.learn.simpleconsoleapp.configs.demo.SingerDocumentaryUsingProxyFactoryBeanConfig;
import com.learn.simpleconsoleapp.models.Agent;
import com.learn.simpleconsoleapp.seedworks.advices.*;
import com.learn.simpleconsoleapp.seedworks.advisor.IsModifiedAdvisor;
import com.learn.simpleconsoleapp.seedworks.annotation.NeedLogging;
import com.learn.simpleconsoleapp.seedworks.mixin.IsModified;
import com.learn.simpleconsoleapp.seedworks.pointcuts.*;
import com.learn.simpleconsoleapp.services.KeyGenerator;
import com.learn.simpleconsoleapp.services.SecurityManager;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringAopApp {
    public static void main(String[] args) {
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

            // Static-Check for "rent()" occur 2 times, one during the initial phase
            // when all methods are checked and another when it is first invoked.
            // Dynamic-Check occur 4 times
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

        {
            System.out.println("\n--------------------------");
            System.out.println("Spring AOP - AspectJ Pointcut - AspectJExpressPointcut");
            System.out.println("--------------------------\n");

            var singer = new BetterSinger();

            var aspectjExpressionPointcut = new AspectJExpressionPointcut();
            aspectjExpressionPointcut.setExpression("execution(* sing*(..))");

            var proxyFactory = new ProxyFactory();
            proxyFactory.addAdvisor(new DefaultPointcutAdvisor(aspectjExpressionPointcut, new ConsoleLoggingAdvice()));
            proxyFactory.setTarget(singer);

            var proxiedSinger = (BetterSinger) proxyFactory.getProxy();

            proxiedSinger.rent(1000);
            proxiedSinger.sing();
        }

        {
            System.out.println("\n--------------------------");
            System.out.println("Spring AOP - Annotation Matching Pointcut");
            System.out.println("--------------------------\n");

            var singer = new BetterSinger();

            var annotationMatchingPointcut = AnnotationMatchingPointcut.forMethodAnnotation(NeedLogging.class);

            var proxyFactory = new ProxyFactory();
            proxyFactory.addAdvisor(new DefaultPointcutAdvisor(annotationMatchingPointcut, new ConsoleLoggingAdvice()));
            proxyFactory.setTarget(singer);

            var proxiedSinger = (BetterSinger) proxyFactory.getProxy();

            proxiedSinger.rent(1000);
            proxiedSinger.setupShow();
            proxiedSinger.sing();
        }

        {
            System.out.println("\n--------------------------");
            System.out.println("Spring AOP - Proxies");
            System.out.println("--------------------------\n");

            var singer = new BetterSinger();

            var advisor = new DefaultPointcutAdvisor(
                    new SupervisorReviewStaticPointcut(), new NoOpBeforeAdvice());

            System.out.println("Running CGLIB (Standard) Tests");
            proxyPerformanceTest(proxyWithCglib(advisor, singer));

            System.out.println("Running CGLIB (Frozen) Tests");
            proxyPerformanceTest(proxyWithCglibFrozen(advisor, singer));

            System.out.println("Running JDK Tests");
            proxyPerformanceTest(proxyWithJdk(advisor, singer));
        }

        {
            System.out.println("\n--------------------------");
            System.out.println("Spring AOP - Advanced Pointcut - ControlFlowPointcut");
            System.out.println("--------------------------\n");

            var singer = new BetterSinger();
            var app = new SpringAopApp();

            var controlFlowPointcut = new ControlFlowPointcut(DeclareSpringComponentsApp.class, "runSingerMethods");
            var advisor = new DefaultPointcutAdvisor(controlFlowPointcut, new ConsoleLoggingAdvice());

            var proxyFactory = new ProxyFactory();
            proxyFactory.setTarget(singer);
            proxyFactory.addAdvisor(advisor);

            var proxiedSinger = (BetterSinger) proxyFactory.getProxy();

            System.out.println("Normally invoke Singer methods:");
            proxiedSinger.sing();
            proxiedSinger.rent(2000);

            System.out.println("\nInvoke Singer methods from runSingerMethods:");
            app.runSingerMethods(proxiedSinger);
        }

        {
            System.out.println("\n--------------------------");
            System.out.println("Spring AOP - Advanced Pointcut -  ComposablePointcut");
            System.out.println("--------------------------\n");

            var singer = new BetterSinger();

            // first, init it own ClassFilter and MethodMatcher
            var composablePointcut = new ComposablePointcut(ClassFilter.TRUE,
                    (MethodMatcher) new BetterSingerReceiveMoneyStaticPointcut());

            System.out.println("Union-Compose Pointcuts:");
            // compose with another pointcut's MethodMatcher
            composablePointcut.union((MethodMatcher) new BetterSingerSingingStaticPointcut());
            var proxiedSinger = (BetterSinger) proxyWithCglib(new DefaultPointcutAdvisor(composablePointcut, new ConsoleLoggingAdvice()), singer);
            proxiedSinger.sing();
            proxiedSinger.rent(2000);

            // union() mean "or" conditional operand

            System.out.println("\nIntersection-Compose Pointcuts:");
            // compose with another pointcut's MethodMatcher
            composablePointcut.intersection((MethodMatcher) new BetterSingerHighRentDynamicPointcut());
            proxiedSinger = (BetterSinger) proxyWithCglib(new DefaultPointcutAdvisor(composablePointcut, new ConsoleLoggingAdvice()), singer);
            proxiedSinger.sing();
            proxiedSinger.rent(500);
            proxiedSinger.rent(1500);

            // intersection() mean "and" conditional operand
        }

        {
            System.out.println("\n--------------------------");
            System.out.println("Spring AOP - Introduction");
            System.out.println("--------------------------\n");

            var singer = new BetterSinger();
            singer.setName("Thomas Edison");

            var introductionAdvisor = new IsModifiedAdvisor();

            // JDK Proxy generate a proxy only for the introduced interface (not the original class)
            // This line of code, cast proxy to Singer, error will occur when using JDK Proxy with Introduction
            var proxiedSinger = (Singer) proxyWithCglib(introductionAdvisor, singer);
            var proxiedMixin = (IsModified) proxiedSinger;

            System.out.println("Is Singer?  " + (proxiedSinger instanceof Singer));
            System.out.println("Is BetterSinger?  " + (proxiedSinger instanceof BetterSinger));
            System.out.println("Has been modified?  " + proxiedMixin.isModified());

            proxiedSinger.setName("Thomas Edison");
            System.out.println("Has been modified?  " + proxiedMixin.isModified());

            proxiedSinger.setName("Eric Clapton");
            System.out.println("Has been modified?  " + proxiedMixin.isModified());

            System.out.println();

            for (var entry : IsModifiedMixinIntroduction.TARGET_OBJECT_METHOD_INVOKE_COUNT.entrySet()) {
                System.out.println("DelegateIntroductionInterceptor dispatches and delegate method invocation of target object or mixin :" +
                        "\n\t - on: " + entry.getKey() +
                        "\n\t - with: " + entry.getValue() + " (times)");
            }
        }

        {
            System.out.println("\n--------------------------");
            System.out.println("Spring AOP - ProxyFactoryBean with XML Dependency Injection");
            System.out.println("--------------------------\n");

            var context = new GenericXmlApplicationContext();
            context.load("classpath:demo/singer-documentary-using-ProxyFactoryBean-context.xml");
            context.refresh();

            var documentaristOne = context.getBean("documentaristOne", Documentarist.class);
            documentaristOne.execute();

            var documentaristTwo = context.getBean("documentaristTwo", Documentarist.class);
            documentaristTwo.execute();

            var proxiedSingerThree = context.getBean("singerProxyThree", Singer.class);
            var proxiedMixin = (IsModified) proxiedSingerThree;

            proxiedSingerThree.setName("John Mayer");
            System.out.println();
            System.out.println("Has been modified? " + proxiedMixin.isModified());

            proxiedSingerThree.setName("Eric Clapton");
            System.out.println();
            System.out.println("Has been modified? " + proxiedMixin.isModified());
        }

        {
            System.out.println("\n--------------------------");
            System.out.println("Spring AOP - ProxyFactoryBean with Java Config Dependency Injection");
            System.out.println("--------------------------\n");

            var context = new AnnotationConfigApplicationContext(SingerDocumentaryUsingProxyFactoryBeanConfig.class);

            var proxiedSingerThree = context.getBean("singerProxyThree", Singer.class);
            var proxiedMixin = (IsModified) proxiedSingerThree;

            proxiedSingerThree.setName("John Mayer");
            System.out.println();
            System.out.println("Has been modified? " + proxiedMixin.isModified());

            proxiedSingerThree.setName("Eric Clapton");
            System.out.println();
            System.out.println("Has been modified? " + proxiedMixin.isModified());
        }
    }

    /**
     * Wrap a {@link Singer} and run its methods
     *
     * @param singer
     */
    private void runSingerMethods(Singer singer) {
        singer.sing();
        singer.rent(2000);
    }

    /**
     * At build-time, CGLIB enhance type T
     * <br/>(with auto generated type, name like <b>T$$EnhancerBySpringCGLIB$$cf61a516</b>)
     * <br/>extend from class T
     * <br/>and implemented interface {@link Advised}
     *
     * @param advisor
     * @param target
     * @param <T>
     * @return
     */
    private static <T> Object proxyWithCglib(Advisor advisor, T target) {
        ProxyFactory pf = new ProxyFactory();
        pf.setProxyTargetClass(true);
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        return pf.getProxy();
    }

    /**
     * At build-time, CGLIB enhance type T
     * <br/>(with auto generated type, name like <b>T$$EnhancerBySpringCGLIB$$cf61a516</b>)
     * <br/>extend from class T
     * <br/>and implemented interface {@link Advised}
     *
     * @param advisor
     * @param target
     * @param <T>
     * @return
     */
    private static <T> Object proxyWithCglibFrozen(Advisor advisor, T target) {
        ProxyFactory pf = new ProxyFactory();
        pf.setProxyTargetClass(true);
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        pf.setFrozen(true);
        return pf.getProxy();
    }

    /**
     * At runtime, JDK not enhance type T,
     * <br/>It wrap target with com.sun.proxy.Proxy class
     * <br/>An proxy instance is implement interfaces of class T and interface {@link Advised}
     *
     * @param advisor
     * @param target
     * @param <T>
     * @return
     */
    private static <T> Object proxyWithJdk(Advisor advisor, T target) {
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        pf.setInterfaces(target.getClass().getInterfaces());
        return pf.getProxy();
    }

    /**
     * Do some performance test for proxied {@link Supervisor}
     * <ul>
     *     <li>CGLIB Proxy</li>
     *     <li>CGLIB Proxy (with frozen config)</li>
     *     <li>JDK Proxy</li>
     * </ul>
     *
     * @param proxiedSupervisor at runtime/build-time its type was enhanced by JDK/CGLIB
     *                          <br/>(with auto generated type, name like <b>com.learn.simpleconsoleapp.beans.BetterSinger$$EnhancerBySpringCGLIB$$cf61a516</b>)
     *                          <br/>extend from class {@link Supervisor}
     *                          <br/>and implemented interface {@link Advised}
     */
    private static void proxyPerformanceTest(Object proxiedSupervisor) {
        long before = 0;
        long after = 0;

        var supervisor = (Supervisor) proxiedSupervisor;

        System.out.println("Testing Advised Method");
        before = System.currentTimeMillis();
        for (int x = 0; x < 500000; x++) {
            supervisor.review();
        }
        after = System.currentTimeMillis();
        System.out.println("Took " + (after - before) + " ms");

        System.out.println("Testing Unadvised Method");
        before = System.currentTimeMillis();
        for (int x = 0; x < 500000; x++) {
            supervisor.training();
        }
        after = System.currentTimeMillis();
        System.out.println("Took " + (after - before) + " ms");

        System.out.println("Testing equals() Method");
        before = System.currentTimeMillis();
        for (int x = 0; x < 500000; x++) {
            supervisor.equals(supervisor);
        }
        after = System.currentTimeMillis();
        System.out.println("Took " + (after - before) + " ms");

        System.out.println("Testing hashCode() Method");
        before = System.currentTimeMillis();
        for (int x = 0; x < 500000; x++) {
            supervisor.hashCode();
        }
        after = System.currentTimeMillis();
        System.out.println("Took " + (after - before) + " ms");

        System.out.println("Testing method on Advised Interface");
        before = System.currentTimeMillis();
        for (int x = 0; x < 500000; x++) {
            ((Advised) supervisor).getTargetClass();
        }
        after = System.currentTimeMillis();
        System.out.println("Took " + (after - before) + " ms");
        System.out.println("\n");
    }
}
