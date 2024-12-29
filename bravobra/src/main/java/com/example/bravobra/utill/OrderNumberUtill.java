package com.example.bravobra.utill;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderNumberUtill {
    private final static AtomicInteger COUNTER = new AtomicInteger(1);

    public static String generateOrderNumber() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        int count = COUNTER.getAndIncrement();
        return String.format("ORD-%s-%04d", date, count); // 예: ORD-20241227-0001
    }
}
