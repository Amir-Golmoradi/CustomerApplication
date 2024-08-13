package dev.amir.masterclass.presentation.controller;

import dev.amir.masterclass.domain.entity.Customer;
import dev.amir.masterclass.domain.service.CustomerService;
import dev.amir.masterclass.infrastructure.Dao.CustomerRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomersFromLocal() {
        return customerService.getAllCustomersFromDAO();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") Integer customerId) {
        return customerService.getCustomerByIdFromDAO(customerId);
    }

    @PostMapping("register")
    public void registerNewCustomer(@RequestBody CustomerRegistrationRequest request) {
        customerService.createNewCustomer(request);

    }

    @DeleteMapping()
    public void deleteCurrentCustomers(CustomerRegistrationRequest request) {
        customerService.deleteCustomers(request);
    }


    @DeleteMapping("/{customerId}")
    public void deleteCurrentCustomersById(@PathVariable("customerId") Integer customerId) {
        customerService.deleteCustomersById(customerId);
    }
}
