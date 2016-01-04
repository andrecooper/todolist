package com.home.todoList.config.initializer;

import com.home.todoList.config.AppConfig;
import com.home.todoList.config.MvcConfig;
import com.home.todoList.config.SecurityConfig;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@PropertySource("classpath:tasklist.properties")
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {



    @Override
    protected ApplicationContextInitializer<?>[] getRootApplicationContextInitializers() {
        return new ApplicationContextInitializer<?>[]{ new ContextInitializer() };
    }

    /*@Override
    protected WebApplicationContext createRootApplicationContext() {
        WebApplicationContext servletApplicationContext = super.createRootApplicationContext();
        String env = environment.getProperty("application.environment");
        ((ConfigurableEnvironment) servletApplicationContext.getEnvironment()).setActiveProfiles("Dev");
        return servletApplicationContext;
    }*/
/*    @Override
    protected WebApplicationContext createServletApplicationContext() {
        WebApplicationContext servletApplicationContext = super.createServletApplicationContext();
        ((ConfigurableEnvironment) servletApplicationContext.getEnvironment()).setActiveProfiles("Dev");

        return servletApplicationContext;
    }*/

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{AppConfig.class, SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{MvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
