package dev.amir.masterclass.domain.service;

import dev.amir.masterclass.domain.entity.Customer;
import dev.amir.masterclass.infrastructure.Dao.CustomerDao;
import dev.amir.masterclass.infrastructure.Dao.CustomerRegistrationRequest;
import dev.amir.masterclass.infrastructure.exception.DuplicationException;
import dev.amir.masterclass.infrastructure.exception.ResourceNotFound;
import dev.amir.masterclass.infrastructure.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerDao;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(@Qualifier("JPA") CustomerDao customerDao, CustomerRepository customerRepository) {
        this.customerDao = customerDao;
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomersFromDAO() {
        return customerDao.selectAllCustomer();
    }

    public Customer getCustomerByIdFromDAO(Integer id) {
        return customerDao.selectCustomerById(id)
                .orElseThrow(
                        () -> new ResourceNotFound("customer with following id [%s] not found".formatted(id))
                );

    }

    public void createNewCustomer(CustomerRegistrationRequest registrationRequest) {
        if (customerDao.existsUserWithEmail(registrationRequest.email())) {
            throw new DuplicationException("Email is already taken");
        }
        Customer customer = new Customer(
                registrationRequest.id(),
                registrationRequest.name(),
                registrationRequest.email(),
                registrationRequest.age()
        );
        customerDao.insertCustomer(customer);
        customerRepository.save(customer);

    }

    public void deleteCustomers(CustomerRegistrationRequest registrationRequest) {
        Customer customer = new Customer(
                registrationRequest.id(),
                registrationRequest.name(),
                registrationRequest.email(),
                registrationRequest.age()
        );
        customerDao.deleteCustomer(customer);
        customerRepository.save(customer);

    }

    public void deleteCustomersById(Integer customerId) {
        customerDao.deleteCustomerById(customerId);
    }


}
