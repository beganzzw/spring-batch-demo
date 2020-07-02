package com.zzw.batch.listener;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Component
public class MyItemReaderListener implements ItemReadListener<String> {
    @Override
    public void beforeRead() {
        System.out.println("before read");
    }

    @Override
    public void afterRead(String s) {
        System.out.println("after read: " + s);
    }

    @Override
    public void onReadError(Exception e) {
        System.out.println("on read error: " + e.getMessage());
    }
}
