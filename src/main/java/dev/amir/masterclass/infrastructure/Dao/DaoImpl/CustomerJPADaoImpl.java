package dev.amir.masterclass.infrastructure.Dao.DaoImpl;

import dev.amir.masterclass.domain.entity.Customer;
import dev.amir.masterclass.infrastructure.Dao.CustomerDao;
import dev.amir.masterclass.infrastructure.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("JPA")
public class CustomerJPADaoImpl implements CustomerDao {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerJPADaoImpl(CustomerRepository customerRepository) {
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
    public boolean existsUserWithEmail(String email) {
        return customerRepository.existsCustomerByEmail(email);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerRepository.deleteAll();
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        customerRepository.deleteById(customerId);

    }
}
