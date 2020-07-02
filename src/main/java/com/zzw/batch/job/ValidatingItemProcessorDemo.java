package com.zzw.batch.job;

import com.zzw.batch.entity.TestData;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ValidatingItemProcessorDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ListItemReader<TestData> simpleReader;

    @Bean
    public Job validatingItemProcessorJob(){
        return jobBuilderFactory.get("validatingItemProcessorJob")
                .start(step())
                .build();
    }

    private Step step(){
        return stepBuilderFactory.get("step")
                .<TestData,TestData>chunk(2)
                .reader(simpleReader)
                .processor(validatingItemProcessor())
                .writer(list -> list.forEach(System.out::println))
                .build();
    }

    private ValidatingItemProcessor<TestData> validatingItemProcessor(){
        ValidatingItemProcessor<TestData> processor = new ValidatingItemProcessor<>();
        processor.setValidator(value->{
            if ("".equals(value.getField3())){
                throw new ValidationException("field3的值不合法");
            }
        });
        return processor;
    }
}
