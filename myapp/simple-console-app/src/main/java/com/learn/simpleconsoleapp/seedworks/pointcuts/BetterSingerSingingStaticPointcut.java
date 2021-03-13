package com.learn.simpleconsoleapp.seedworks.pointcuts;

import com.learn.simpleconsoleapp.beans.BetterSinger;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * {@link org.springframework.aop.support.StaticMethodMatcherPointcut} is a convenient Pointcut
 * implementation, which is also a static MethodMatcher itself.
 *
 * @see org.springframework.aop.support.StaticMethodMatcherPointcut
 * @see com.learn.simpleconsoleapp.beans.Singer
 * @see com.learn.simpleconsoleapp.beans.BetterSinger
 * @see com.learn.simpleconsoleapp.beans.GreatSinger
 */
public class BetterSingerSingingStaticPointcut extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return "sing".equals(method.getName());
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
