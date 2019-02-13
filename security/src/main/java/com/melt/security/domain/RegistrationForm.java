package com.melt.security.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationForm {

    private String username;
    private String password;
    private String email;
    private String phone;
    private boolean status = false;

    public Customer toCustomer(PasswordEncoder passwordEncoder){
        Customer customer = new Customer();

        customer.setUsername(username);
        customer.setPassword(passwordEncoder.encode(password));
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setStatus(true);
        customer.setRoles(Collections.singleton(Role.USER));

        return customer;
    }
}
