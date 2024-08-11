package dev.amir.masterclass;

import dev.amir.masterclass.domain.entity.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringMasterClassApplication {

    private static List<Customer> customers;

    static {
        customers = new ArrayList<>();
        Customer leo = new Customer(
                1, "Leo", "LeoQueen@victus.com", 30
        );
        Customer maria = new Customer(
                2, "maria", "mariasantos@victus.com", 21
        );
        customers.add(leo);
        System.out.printf("---Our customers are here %s \t %s---", leo, maria);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringMasterClassApplication.class, args);
    }
}
