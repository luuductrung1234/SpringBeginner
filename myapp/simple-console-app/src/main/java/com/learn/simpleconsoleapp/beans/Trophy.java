package com.learn.simpleconsoleapp.beans;

import org.springframework.core.annotation.AliasFor;

/**
 * {@link com.learn.simpleconsoleapp.beans.Trophy} annotation declares an attribute named <i>name<i/>, which
 * is an alias for the <i>value</i> attribute of the {@link com.learn.simpleconsoleapp.beans.Award} annotation
 */
@Award
public @interface Trophy {
    @AliasFor(annotation = Award.class, attribute = "value")
    String[] name() default {};
}
