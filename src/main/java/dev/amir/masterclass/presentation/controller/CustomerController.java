package dev.amir.masterclass.presentation.controller;

import dev.amir.masterclass.domain.entity.Customer;
import dev.amir.masterclass.domain.usecase.interfaces.ICustomerUseCase;
import dev.amir.masterclass.infrastructure.custom_actions.CustomerRegistrationRequest;
import dev.amir.masterclass.infrastructure.custom_actions.CustomerUpdateRequest;
import dev.amir.masterclass.infrastructure.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final JwtUtils jwtUtils;
    private final ICustomerUseCase customerUseCase;

    @GetMapping
    public List<Customer> getAllCustomersFromLocal() {
        return customerUseCase.getAllCustomerUseCase();
    }

    @GetMapping("{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") Integer customerId) {
        return customerUseCase.getCustomerByIdUseCase(customerId);
    }

    @PostMapping("register")
    public ResponseEntity<?> registerNewCustomer(@RequestBody CustomerRegistrationRequest request) {
        customerUseCase.createCustomerUseCase(request);
        String jwtToken = jwtUtils.issueToken(request.email(), "ROLE_USER");
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtToken)
                .build();
    }

    @DeleteMapping()
    public void deleteCurrentCustomers(CustomerRegistrationRequest request) {
        customerUseCase.deleteAllCustomerUseCase(request);
    }

    @DeleteMapping("{customerId}")
    public void deleteCurrentCustomersById(@PathVariable("customerId") Integer customerId) {
        customerUseCase.deleteUserByIdSUseCase(customerId);
    }

    @PutMapping("{customerId}")
    public void updateCurrentCustomer(@RequestBody CustomerUpdateRequest updateRequest,
                                      @PathVariable("customerId") Integer customerId) {
        customerUseCase.updateCustomerUseCase(customerId, updateRequest);
    }

}
