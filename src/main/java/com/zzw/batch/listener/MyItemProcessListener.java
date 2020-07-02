package com.zzw.batch.listener;

import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

@Component
public class MyItemProcessListener implements ItemProcessListener<String,String> {
    @Override
    public void beforeProcess(String s) {
        System.out.println("before process: " + s);
    }

    @Override
    public void afterProcess(String s, String s2) {
        System.out.println("after process: " + s + " result: " + s2);
    }

    @Override
    public void onProcessError(String s, Exception e) {
        System.out.println("on process error: " + s + " , error message: " + e.getMessage());
    }
}
