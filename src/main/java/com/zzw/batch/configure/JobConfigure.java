package com.zzw.batch.configure;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfigure {

    @Bean
    public JobRegistryBeanPostProcessor postProcessor(JobRegistry jobRegistry, ApplicationContext applicationContext){
        JobRegistryBeanPostProcessor postProcessor = new JobRegistryBeanPostProcessor();
        postProcessor.setJobRegistry(jobRegistry);
        postProcessor.setBeanFactory(applicationContext.getAutowireCapableBeanFactory());
        return postProcessor;
    }
}
