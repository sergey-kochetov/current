package com.melt.star.service;

import com.melt.star.model.user.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class BossService {

    @Autowired
    private RestTemplate restTemplate;

    public Answer getAnswerById(String bossId) {
        return restTemplate.getForObject("http://localhost:4000/api/{id}", Answer.class, bossId);
    }

    @Async
    public CompletableFuture<Answer> getAnswerByIdAsync(String bossId) {
        Answer answer = restTemplate.getForObject("http://localhost:4000/api/{id}", Answer.class, bossId);
        return CompletableFuture.completedFuture(answer);
    }


}
