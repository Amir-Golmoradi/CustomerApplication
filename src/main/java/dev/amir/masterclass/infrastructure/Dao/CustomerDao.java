package dev.amir.masterclass.infrastructure.Dao;

import dev.amir.masterclass.domain.entity.Customer;

import java.util.List;
import java.util.Optional;


public interface CustomerDao {
    List<Customer> selectAllCustomer();

    Optional<Customer> selectCustomerById(Integer customerId);

    void insertCustomer(Customer customer);

    boolean existsUserWithEmail(String email);

    void deleteCustomer(Customer customer);

    void deleteCustomerById(Integer customerId);


}

