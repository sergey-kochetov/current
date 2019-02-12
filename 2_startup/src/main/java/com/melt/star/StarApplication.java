package com.melt.star;

import com.melt.star.model.Buyer;
import com.melt.star.repo.BuyerRepoJDBC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class StarApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StarApplication.class, args);

        BuyerRepoJDBC buyerRepoJDBC = context.getBean(BuyerRepoJDBC.class);
        buyerRepoJDBC.save(new Buyer(1L, "Serg", "RU", 100));
        buyerRepoJDBC.save(new Buyer(2L, "Taya", "RU", 120));

        Buyer byId = buyerRepoJDBC.findById("1");
        System.out.println(byId);
        buyerRepoJDBC.findAll().forEach(System.out::println);
    }
}
