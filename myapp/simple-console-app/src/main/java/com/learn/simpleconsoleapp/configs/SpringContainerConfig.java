package com.learn.simpleconsoleapp.configs;

import com.learn.simpleconsoleapp.beans.FullName;
import com.learn.simpleconsoleapp.seedworks.CustomPropertyEditorRegistrar;
import com.learn.simpleconsoleapp.seedworks.FullNamePropertyEditor;
import com.learn.simpleconsoleapp.seedworks.InspectRegisteredScopesPostProcessor;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.SimpleThreadScope;

import java.util.HashMap;

@Configuration
public class SpringContainerConfig {
    @Bean
    public static CustomScopeConfigurer getCustomScopeConfigurer() {
        var customScopeConfigurer = new CustomScopeConfigurer();
        customScopeConfigurer.addScope("thread", new SimpleThreadScope());
        return customScopeConfigurer;
    }

    @Bean
    public static InspectRegisteredScopesPostProcessor getInspectScopesPostProcessor() {
        return new InspectRegisteredScopesPostProcessor();
    }

    @Bean
    public static CustomEditorConfigurer getCustomEditorConfigurer() {
        var customEditorConfigurer = new CustomEditorConfigurer();

        customEditorConfigurer.setPropertyEditorRegistrars(
                new PropertyEditorRegistrar[]{new CustomPropertyEditorRegistrar()});

        customEditorConfigurer.setCustomEditors(new HashMap<>() {{
            put(FullName.class, FullNamePropertyEditor.class);
        }});

        return customEditorConfigurer;
    }
}
