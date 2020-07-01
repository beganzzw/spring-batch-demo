package com.zzw.batch.job;

import com.zzw.batch.entity.TestData;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Component
public class XmlFileItemWriterDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ListItemReader<TestData> simpleReader;

    @Bean
    public Job xmlFileItemWriterJob() throws IOException {
        return jobBuilderFactory.get("xmlFileItemWriterJob")
                .start(step())
                .build();
    }


    private Step step() throws IOException {
        return stepBuilderFactory.get("step")
                .<TestData,TestData>chunk(2)
                .reader(simpleReader)
                .writer(xmlFileItemWriter())
                .build();
    }

    private StaxEventItemWriter<TestData> xmlFileItemWriter() throws IOException {
        StaxEventItemWriter<TestData> writer = new StaxEventItemWriter<>();

        XStreamMarshaller marshaller = new XStreamMarshaller();

        Map<String,Class<TestData>> map = new HashMap<>(1);
        map.put("test",TestData.class);

        marshaller.setAliases(map);

        writer.setRootTagName("tests");
        writer.setMarshaller(marshaller);

        //需改成对应的本地路径
        FileSystemResource file = new FileSystemResource("C:/Users/lenovo/Desktop/file.txt");
        Path path = Paths.get(file.getPath());
        if (!Files.exists(path)){
            Files.createFile(path);
        }
        writer.setResource(file);
        return writer;
    }
}
