package dev.amir.masterclass.domain.usecase.implemetations;

import dev.amir.masterclass.domain.entity.Customer;
import dev.amir.masterclass.domain.service.CustomerService;
import dev.amir.masterclass.domain.usecase.interfaces.ICustomerUseCase;
import dev.amir.masterclass.infrastructure.custom_actions.CustomerRegistrationRequest;
import dev.amir.masterclass.infrastructure.custom_actions.CustomerUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerUseCaseService implements ICustomerUseCase {

    private final CustomerService customerService;


    @Override
    public void createCustomerUseCase(CustomerRegistrationRequest request) {
        customerService.createNewCustomer(request);
    }

    @Override
    public List<Customer> getAllCustomerUseCase() {
        return customerService.getAllCustomers();
    }

    @Override
    public Customer getCustomerByIdUseCase(Integer userId) {
        return customerService.getCustomerById(userId);
    }

    @Override
    public void updateCustomerUseCase(Integer id, CustomerUpdateRequest updateRequest) {
        customerService.updateCustomer(id, updateRequest);
    }

    @Override
    public void deleteAllCustomerUseCase(CustomerRegistrationRequest request) {
            customerService.deleteCustomers(request);
    }

    @Override
    public void deleteUserByIdSUseCase(Integer customerId) {
        customerService.deleteCustomersById(customerId);
    }
}
