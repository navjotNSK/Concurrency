package com.navjot.Concurrency.service;

import com.navjot.Concurrency.util.Counter;
import com.navjot.Concurrency.util.SynchronisedCounter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SchedulerService {

    @Autowired
    @Qualifier("ReentrantLockCounter")
    private Counter counter;

    @Async
    @Scheduled(cron = "*/2 * * * * *")
    public void scheduleTask() throws InterruptedException {


        Thread t1 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }catch (Exception e){
            log.error(e.getMessage());
        }

        System.out.println("Print Time - " + System.currentTimeMillis() + " " + "Final count: " + counter.getCount());
//        System.out.println("Print Time - " + System.currentTimeMillis());
//        log.info("Print Time - " + System.currentTimeMillis());
    }
}
