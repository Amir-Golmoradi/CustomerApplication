package dev.amir.masterclass.domain.usecase.interfaces;

import dev.amir.masterclass.domain.entity.Customer;
import dev.amir.masterclass.infrastructure.custom_actions.CustomerRegistrationRequest;
import dev.amir.masterclass.infrastructure.custom_actions.CustomerUpdateRequest;

import java.util.List;

public interface ICustomerUseCase {
    void createCustomerUseCase(CustomerRegistrationRequest request);

    List<Customer> getAllCustomerUseCase();

    Customer getCustomerByIdUseCase(Integer userId);

    void updateCustomerUseCase(Integer id, CustomerUpdateRequest updateRequest);
    void deleteAllCustomerUseCase(CustomerRegistrationRequest request);
    void deleteUserByIdSUseCase(Integer userId);
}
