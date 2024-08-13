package dev.amir.masterclass.infrastructure.repository;

import dev.amir.masterclass.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository
        extends JpaRepository<Customer, Integer> {

    boolean existsCustomerByEmail(String email);

}
