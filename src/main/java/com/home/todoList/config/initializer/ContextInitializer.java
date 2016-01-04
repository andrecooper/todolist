package com.home.todoList.config.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class ContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

/*
    @Autowired
    Environment environment;
*/

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
/*        String env = environment.getProperty("application.environment");
        System.out.println("SETTING ACTIVE PROFILE: " + env);*/
        configurableApplicationContext.getEnvironment().setActiveProfiles("Dev");
    }
}
