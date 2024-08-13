package dev.amir.masterclass.infrastructure.Dao.DaoImpl;

import dev.amir.masterclass.domain.entity.Customer;
import dev.amir.masterclass.infrastructure.Dao.CustomerDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeCustomerDaoImpl implements CustomerDao {

    private static final List<Customer> customers;

    static {
        customers = new ArrayList<>();
        Customer leo = new Customer(1, "Leo", "LeoQueen@victus.com", 30);
        Customer maria = new Customer(2, "maria", "mariasantos@victus.com", 21);
        customers.add(leo);
    }


    @Override
    public List<Customer> selectAllCustomer() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return customers.stream()
                .filter(customerId -> customerId.getId()
                        .equals(id)).findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existsUserWithEmail(String email) {
        return
                customers.stream()
                        .anyMatch(customerEmail -> customerEmail.getEmail().equals(email));
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customers.remove(customer);
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        customers.removeIf(customer -> customer.getId().equals(customerId));

    }
}
