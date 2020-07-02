package com.zzw.batch.job;


import com.zzw.batch.entity.TestData;
import com.zzw.batch.processor.TestDataTransformItemPorcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TestDataTransformItemProcessorDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ListItemReader<TestData> simpleReader;

    @Autowired
    private TestDataTransformItemPorcessor testDataTransformItemPorcessor;

    @Bean
    public Job testDataTransformItemProcessorJob(){
        return jobBuilderFactory.get("testDataTransformItemProcessorJob")
                .start(step())
                .build();
    }

    private Step step(){
        return stepBuilderFactory.get("step")
                .<TestData,TestData>chunk(2)
                .reader(simpleReader)
                .processor(testDataTransformItemPorcessor)
                .writer(list -> list.forEach(System.out::println))
                .build();
    }
}
