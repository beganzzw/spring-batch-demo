package com.zzw.batch.processor;

import com.zzw.batch.entity.TestData;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class TestDataTransformItemPorcessor   implements ItemProcessor<TestData,TestData> {
    @Override
    public TestData process(TestData testData) throws Exception {
        testData.setField1(testData.getField1() + "hello");
        return testData;
    }
}
