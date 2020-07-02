package com.zzw.batch.processor;

import com.zzw.batch.entity.TestData;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class TestDataFilterItemProcessor implements ItemProcessor<TestData,TestData> {

    @Override
    public TestData process(TestData testData) throws Exception {
        return "".equals(testData.getField3()) ? null :testData;
    }
}
