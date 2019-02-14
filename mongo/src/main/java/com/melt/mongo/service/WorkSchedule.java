package com.melt.mongo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WorkSchedule {

    private static int count = 1;


    // 5 sec
    //@Scheduled(initialDelay = 1000, fixedDelayString = "5000")
    //@Scheduled(cron = "*/5 * * * * ?")
    public void runFirst() throws InterruptedException {
        log.info("First start: " + count++);
        Thread.sleep(5000);
        log.info("First finish: " + count);
    }

    //@Scheduled(fixedRateString = "${schedule.work}")
    public void runSecond() throws InterruptedException {
        log.info("Second start: "+ count++);
        Thread.sleep(3000);
        log.info("Second finish: "+ count);
    }
}
