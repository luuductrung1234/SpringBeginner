package com.learn.simpleconsoleapp.seedworks.pointcuts;

import com.learn.simpleconsoleapp.beans.BetterSinger;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * {@link org.springframework.aop.support.DynamicMethodMatcherPointcut} is a convenient Pointcut
 * implementation, which is also a dynamic MethodMatcher itself.
 *
 * @see org.springframework.aop.support.DynamicMethodMatcherPointcut
 * @see com.learn.simpleconsoleapp.beans.Singer
 * @see com.learn.simpleconsoleapp.beans.BetterSinger
 * @see com.learn.simpleconsoleapp.beans.GreatSinger
 */
public class BetterSingerHighRentDynamicPointcut extends DynamicMethodMatcherPointcut {
    public static final Map<String, Integer> METHOD_STATIC_CHECKING_COUNT = new HashMap<>();
    public static final Map<String, Integer> METHOD_DYNAMIC_CHECKING_COUNT = new HashMap<>();

    private static final float HIGH_INDICATOR = 1000.0f;

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        var methodFullName = targetClass.getName() + "." + method.getName() + "()";
        var count = METHOD_DYNAMIC_CHECKING_COUNT.getOrDefault(methodFullName, 0);
        METHOD_DYNAMIC_CHECKING_COUNT.put(methodFullName, count + 1);

        float salary = (Float) args[0];

        return (salary > HIGH_INDICATOR);
    }

    /**
     * Don't forget to prudent to implement {@link org.springframework.aop.MethodMatcher#matches(Method, Class)}
     * method to control the behavior of the static checks.
     *
     * the static check's role here is to filter out the methods
     * that will not be applied by dynamic check
     *
     * @param method
     * @param targetClass
     * @return
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        var methodFullName = targetClass.getName() + "." + method.getName() + "()";
        var count = METHOD_STATIC_CHECKING_COUNT.getOrDefault(methodFullName, 0);
        METHOD_STATIC_CHECKING_COUNT.put(methodFullName, count + 1);

        return "rent".equals(method.getName());
    }

    /**
     * Force the advice execution only on the methods of the correct type.
     * <p>
     * Here we want the advice associated with this pointcut will only
     * executed on type {@link com.learn.simpleconsoleapp.beans.BetterSinger}
     *
     * @return
     */
    @Override
    public ClassFilter getClassFilter() {
        return clazz -> {
            // System.out.println("~ filtering class:" + clazz.getName());
            return (clazz == BetterSinger.class);
        };
    }
}
