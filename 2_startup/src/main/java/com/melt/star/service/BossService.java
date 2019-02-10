package com.melt.star.service;

import com.melt.star.model.user.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BossService {

    @Autowired
    private RestTemplate restTemplate;

    public Answer getAnswerById(String bossId) {
        return restTemplate.getForObject("http://localhost:4000/api/{id}", Answer.class, bossId);
    }


}
