package com.melt.security.events;

import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@RestController
public class BNControllerRest {

    private final Set<SseEmitter> subscribers = new CopyOnWriteArraySet<>();

    @GetMapping("/news-stream")
    public SseEmitter events() {
        SseEmitter emitter = new SseEmitter();
        subscribers.add(emitter);

        emitter.onTimeout(() -> subscribers.remove(emitter));
        emitter.onCompletion(() -> subscribers.remove(emitter));
        return emitter;
    }

    @Async
    @EventListener
    public void handleNews(BreakingNews news) {
        List<SseEmitter> deadEmitters = new LinkedList<>();

        subscribers.forEach(emitter -> {
            try {
                emitter.send(news, MediaType.APPLICATION_JSON);
            } catch (IOException e) {
                deadEmitters.add(emitter);
            }
        });

        subscribers.removeAll(deadEmitters);
    }
}
