package dev.amir.masterclass.domain.service;

import dev.amir.masterclass.domain.entity.Customer;
import dev.amir.masterclass.infrastructure.Dao.interfaces.customer.ICustomerDao;
import dev.amir.masterclass.infrastructure.exception.DuplicationException;
import dev.amir.masterclass.infrastructure.exception.RequestValidException;
import dev.amir.masterclass.infrastructure.exception.ResourceNotFound;
import dev.amir.masterclass.infrastructure.custom_actions.CustomerRegistrationRequest;
import dev.amir.masterclass.infrastructure.custom_actions.CustomerUpdateRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final ICustomerDao customerDao;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(@Qualifier("customerJdbc") ICustomerDao customerDao, PasswordEncoder passwordEncoder) {
        this.customerDao = customerDao;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Customer> getAllCustomers() {
        return customerDao.selectAllCustomer();

    }

    public Customer getCustomerById(Integer id) {
        return customerDao.selectCustomerById(id)
                .orElseThrow(
                        () -> new ResourceNotFound("customer with following id [%s] not found".formatted(id)));

    }

    public void createNewCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        String email = customerRegistrationRequest.email();
        if (customerDao.existsCustomerWithEmail(email)) {
            throw new DuplicationException("Email is already taken");
        }
        Customer customer = new Customer(
                customerRegistrationRequest.id(),
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                passwordEncoder.encode(customerRegistrationRequest.password()),
                customerRegistrationRequest.age()
        );
        // add
        customerDao.insertCustomer(customer);

    }

    public void deleteCustomers(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = new Customer(
                customerRegistrationRequest.id(),
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                customerRegistrationRequest.password(),
                customerRegistrationRequest.age());
        customerDao.deleteCustomer(customer);

    }

    public void deleteCustomersById(Integer customerId) {
        if (!customerDao.existsCustomerById(customerId)) {
            throw new ResourceNotFound(
                    "customer with id [%s] not found".formatted(customerId));
        }
        customerDao.deleteCustomerById(customerId);
    }

    public void updateCustomer(Integer customerId, CustomerUpdateRequest updateRequest) {
        boolean hasChanged = false;
        Customer customer = getCustomerById(customerId);
        // IF There is a Customer. AND
        if (updateRequest.name() != null && !updateRequest.name().equals(customer.getName())) {
            customer.setName(updateRequest.name());
            hasChanged = true;
        }
        if (updateRequest.age() != null && !updateRequest.age().equals(customer.getAge())) {
            customer.setAge(updateRequest.age());
            hasChanged = true;
        }
        if (updateRequest.email() != null && !updateRequest.email().equals(customer.getEmail())) {
            if (customerDao.existsCustomerWithEmail(updateRequest.email())) {
                throw new DuplicationException(
                        "user with this email [%s] already taken".formatted(updateRequest.email()));
            }
            customer.setEmail(updateRequest.email());
            hasChanged = true;
        }
        if (!hasChanged) {
            throw new RequestValidException("no data changes found");
        }
        customerDao.updateCustomer(customer);
    }

}
