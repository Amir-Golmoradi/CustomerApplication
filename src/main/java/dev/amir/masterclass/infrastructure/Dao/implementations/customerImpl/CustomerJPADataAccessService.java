package dev.amir.masterclass.infrastructure.Dao.implementations.customerImpl;

import dev.amir.masterclass.domain.entity.Customer;
import dev.amir.masterclass.infrastructure.Dao.interfaces.customer.ICustomerDao;
import dev.amir.masterclass.infrastructure.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class CustomerJPADataAccessService implements ICustomerDao {

    private final ICustomerRepository customerRepository;

    @Autowired
    public CustomerJPADataAccessService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public List<Customer> selectAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public void insertCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public boolean existsCustomerWithEmail(String email) {
        return customerRepository.existsCustomerByEmail(email);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public boolean existsCustomerById(Integer customerId) {
        return customerRepository.existsCustomerById(customerId);
    }

    @Override
    public void updateCustomer(Customer updatedCustomer) {
        customerRepository.save(updatedCustomer);
    }

    @Override
    public Optional<Customer> selectCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }
}
