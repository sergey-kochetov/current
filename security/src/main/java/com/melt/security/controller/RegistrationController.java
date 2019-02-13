package com.melt.security.controller;

import com.melt.security.domain.RegistrationForm;
import com.melt.security.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String registerUser(RegistrationForm form) {
        customerRepo.save(form.toCustomer(passwordEncoder));

        return "redirect:/login";
    }
}
