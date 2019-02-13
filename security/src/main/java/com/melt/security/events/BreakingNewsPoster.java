package com.melt.security.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Component
public class BreakingNewsPoster {

    @Autowired
    private ApplicationEventPublisher publisher;

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @PostConstruct
    public void start() {
        executorService.schedule(this::action, 5, TimeUnit.SECONDS);
    }

    private void action() {
        BreakingNews news = new BreakingNews();
        int sec = ThreadLocalRandom.current().nextInt(5);
        news.setData("news:" + sec);
        publisher.publishEvent(news);
        executorService.schedule(this::action, sec, TimeUnit.SECONDS);
    }


}
