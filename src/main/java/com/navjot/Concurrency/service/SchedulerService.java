package com.navjot.Concurrency.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SchedulerService {

    @Async
    @Scheduled(cron = "*/2 * * * * *")
    public void scheduleTask(){
        System.out.println("Print Time - " + System.currentTimeMillis());
        log.info("Print Time - " + System.currentTimeMillis());
    }
}
