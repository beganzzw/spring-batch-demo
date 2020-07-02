package com.zzw.batch.listener;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;


@Component
public class MyChunkListener implements ChunkListener {
    @Override
    public void beforeChunk(ChunkContext chunkContext) {
        System.out.println("before chunk:" + chunkContext.getStepContext().getStepName());
    }

    @Override
    public void afterChunk(ChunkContext chunkContext) {
        System.out.println("after chunk: " + chunkContext.getStepContext().getStepName());
    }

    @Override
    public void afterChunkError(ChunkContext chunkContext) {
        System.out.println("before chunk error: " + chunkContext.getStepContext().getStepName());
    }
}
