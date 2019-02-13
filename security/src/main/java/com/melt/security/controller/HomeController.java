package com.melt.security.controller;

import com.melt.security.domain.Customer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String indexPage(@AuthenticationPrincipal Customer customer, Model model) {
        if (customer != null) {
            model.addAttribute("customer", customer.getUsername());
        } else {
            model.addAttribute("customer", "anonymous");
        }
        return "index";
    }

    @GetMapping("login")
    public String loginPage() {
        return "login";
    }

    @PreAuthorize(value = "hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("user")
    public String userPage() {
        return "user";
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @GetMapping("admin")
    public String adminPage() {
        return "admin";
    }
}
