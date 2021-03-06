package com.learn.simpleconsoleapp.seedworks.pointcuts;

import com.learn.simpleconsoleapp.beans.GreatSinger;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * {@link org.springframework.aop.support.StaticMethodMatcherPointcut} is a convenient Pointcut
 * implementation, which is also a static MethodMatcher itself.
 *
 * @see org.springframework.aop.support.StaticMethodMatcherPointcut
 * @see com.learn.simpleconsoleapp.beans.Singer
 * @see com.learn.simpleconsoleapp.beans.BetterSinger
 * @see com.learn.simpleconsoleapp.beans.GreatSinger
 */
public class GreatSingerSingingStaticPointcut extends StaticMethodMatcherPointcut {
    public static final Map<String, Integer> METHOD_CHECKING_COUNT = new HashMap<>();

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        var methodFullName = targetClass.getName() + "." + method.getName() + "()";
        var count = METHOD_CHECKING_COUNT.getOrDefault(methodFullName, 0);
        METHOD_CHECKING_COUNT.put(methodFullName, count + 1);

        return "sing".equals(method.getName());
    }

    /**
     * Force the advice execution only on the methods of the correct type.
     * <p>
     * Here we want the advice associated with this pointcut will only
     * executed on type {@link com.learn.simpleconsoleapp.beans.GreatSinger}
     *
     * @return
     */
    @Override
    public ClassFilter getClassFilter() {
        return clazz -> {
            // System.out.println("~ filtering class:" + clazz.getName());
            return (clazz == GreatSinger.class);
        };
//        return clazz -> (clazz.getAnnotation(Trophy.class) != null)
//                && (clazz.getAnnotation(Trophy.class).name().length > 1)
//                && (clazz.getAnnotation(Trophy.class).name()[0] == "grammy")
//                && (clazz.getAnnotation(Trophy.class).name()[1] == "diamond disk");
    }
}
