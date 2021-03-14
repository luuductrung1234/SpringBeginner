package com.learn.simpleconsoleapp.seedworks.advices;

import com.learn.simpleconsoleapp.seedworks.mixin.IsModified;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * A mixin implementation for <b>Object modification detection</b> technique,
 * using Spring AOP Introduction (a special type of around advice)
 *
 * @see IsModified
 * @see com.learn.simpleconsoleapp.seedworks.advisor.IsModifiedAdvisor
 * @see DelegatingIntroductionInterceptor
 * @see org.springframework.aop.support.DefaultIntroductionAdvisor
 */
public class IsModifiedMixinIntroduction extends DelegatingIntroductionInterceptor implements IsModified {
    private boolean isModified = false;

    private Map<Method, Method> methodCached = new HashMap<>();

    public static final Map<String, Integer> TARGET_OBJECT_METHOD_INVOKE_COUNT = new HashMap<>();

    @Override
    public boolean isModified() {
        return isModified;
    }

    /**
     * Override {@link DelegatingIntroductionInterceptor#invoke(MethodInvocation)} to perform custom
     * behaviour in around advice.
     * <br/>
     * However, should call a <code>super.invoke(mi)</code>, which handles introduced interfaces
     * and forwarding to the target, by dispatches the invocation to the correct location, either
     * the advised object or the mixin itself.
     *
     * @param mi
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        trackMethodInvocation(mi);

        if (!isModified) {
            if (mi.getMethod().getName().startsWith("set") && (mi.getArguments().length == 1)) {
                var getter = getGetter(mi.getMethod());

                // detecting modification
                if (getter != null) {
                    var newVal = mi.getArguments()[0];
                    var oldVal = getter.invoke(mi.getThis(), null);

                    if (newVal == null && oldVal == null) {
                        isModified = false;
                    } else if (newVal == null && oldVal != null) {
                        isModified = true;
                    } else if (newVal != null && oldVal == null) {
                        isModified = true;
                    } else {
                        isModified = !newVal.equals(oldVal);
                    }
                }
            }
        }

        return super.invoke(mi);
    }

    /**
     * use JavaBean conventions to get Getter from Setter
     *
     * @param setter
     * @return
     */
    private Method getGetter(Method setter) {
        Method getter = methodCached.get(setter);

        if (getter != null)
            return getter;

        String getterName = setter.getName().replace("set", "get");

        try {
            getter = setter.getDeclaringClass().getMethod(getterName, null);
            synchronized (methodCached) {
                methodCached.put(setter, getter);
            }
            return getter;
        } catch (NoSuchMethodException ex) {
            return null;
        }
    }

    private void trackMethodInvocation(MethodInvocation mi) {
        var methodFullName = mi.getThis().getClass().getName() + "." + mi.getMethod().getName() + "()";
        var count = TARGET_OBJECT_METHOD_INVOKE_COUNT.getOrDefault(methodFullName, 0);
        TARGET_OBJECT_METHOD_INVOKE_COUNT.put(methodFullName, count + 1);
    }
}
