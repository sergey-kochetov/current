package com.melt.security.events;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/news")
public class BreakingNewsController {

    @GetMapping
    public String news() {
        return "news";
    }
}
