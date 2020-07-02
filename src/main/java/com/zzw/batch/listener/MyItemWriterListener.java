package com.zzw.batch.listener;


import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyItemWriterListener  implements ItemWriteListener<String> {
    @Override
    public void beforeWrite(List<? extends String> list) {
        System.out.println("before write:" + list);
    }

    @Override
    public void afterWrite(List<? extends String> list) {
        System.out.println("after write:" + list);
    }

    @Override
    public void onWriteError(Exception e, List<? extends String> list) {
        System.out.println("on write error:" + list + "error message:" + e.getMessage());
    }
}
