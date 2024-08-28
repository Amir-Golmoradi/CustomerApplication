package dev.amir.masterclass.infrastructure.Dao.interfaces.customer;

import dev.amir.masterclass.domain.entity.Customer;

import java.util.List;
import java.util.Optional;


public interface ICustomerDao {
    List<Customer> selectAllCustomer();

    Optional<Customer> selectCustomerById(Integer customerId);

    void insertCustomer(Customer customer);

    boolean existsCustomerWithEmail(String email);

    void deleteCustomer(Customer customer);

    void deleteCustomerById(Integer customerId);

    boolean existsCustomerById(Integer customerId);

    void updateCustomer(Customer updatedCustomer);

    Optional<Customer> selectCustomerByEmail(String email);

}

