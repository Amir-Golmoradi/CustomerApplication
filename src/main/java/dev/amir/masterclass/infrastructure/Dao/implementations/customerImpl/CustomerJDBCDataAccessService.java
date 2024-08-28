package dev.amir.masterclass.infrastructure.Dao.implementations.customerImpl;

import dev.amir.masterclass.domain.entity.Customer;
import dev.amir.masterclass.infrastructure.Dao.interfaces.customer.ICustomerDao;
import dev.amir.masterclass.infrastructure.custom_actions.CustomerRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("customerJdbc")
@RequiredArgsConstructor
public class CustomerJDBCDataAccessService implements ICustomerDao {

    private final JdbcTemplate jdbcTemplate;
    private final CustomerRowMapper rowMapper;


    @Override
    public List<Customer> selectAllCustomer() {
        String query = """
                    SELECT * FROM customer
                """;

        return jdbcTemplate.query(query, rowMapper);
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer customerId) {
        String query = """
                    SELECT customer_id,customer_name,customer_email,customer_age
                    FROM customer
                    WHERE customer_id = ?;
                """;
        return jdbcTemplate
                .query(query, rowMapper, customerId)
                .stream()
                .findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        String query = """
                    INSERT INTO customer(customer_id,customer_name,customer_email,customer_password,customer_age)
                    VALUES(?,?,?,?,?)
                """;
        jdbcTemplate.update(query, customer.getId(), customer.getName(), customer.getEmail(), customer.getPassword(), customer.getAge());
    }

    @Override
    public boolean existsCustomerWithEmail(String email) {
        String query = """
                    SELECT count(customer_id)
                    FROM customer
                    WHERE customer_email = ?
                """;
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, email);
        return count != null && count > 0;
    }

    @Override
    public void deleteCustomer(Customer customer) {
        String query = """
                    DELETE FROM customer;
                """;
        jdbcTemplate.update(query);
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        String query = """
                DELETE
                FROM customer
                WHERE customer_id = ?
                """;
        jdbcTemplate.update(query, customerId);
    }

    @Override
    public boolean existsCustomerById(Integer customerId) {
        String query = """
                    SELECT count(customer_id)
                    FROM customer
                    WHERE customer_id = ?
                """;
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, customerId);
        return count != null && count > 0;
    }

    @Override
    public void updateCustomer(Customer updatedCustomer) {
        if (updatedCustomer.getName() != null) {
            String query = """
                    UPDATE customer SET customer_name = ? WHERE customer_id = ?
                    """;
            int updatedName = jdbcTemplate.update(query, updatedCustomer.getName(), updatedCustomer.getId());
            System.out.println("--- CUSTOMER NAME UPDATED --- " + updatedName);
        }
        if (updatedCustomer.getAge() != null) {
            String query = """
                    UPDATE customer SET customer_age = ? WHERE customer_id = ?
                    """;
            int updatedAge = jdbcTemplate.update(query, updatedCustomer.getAge(), updatedCustomer.getId());
            System.out.println("--- CUSTOMER AGE UPDATED --- " + updatedAge);
        }
        if (updatedCustomer.getEmail() != null) {
            String query = """
                    UPDATE customer SET customer_email = ? WHERE customer_id = ?
                    """;
            int updatedEmail = jdbcTemplate.update(query, updatedCustomer.getEmail(), updatedCustomer.getId());
            System.out.println("--- CUSTOMER EMAIL UPDATED --- " + updatedEmail);
        }
    }

    @Override
    public Optional<Customer> selectCustomerByEmail(String email) {
        String query = """
                    SELECT customer_id,customer_name,customer_email,customer_age
                    FROM customer
                    WHERE customer_email = ?
                """;
        return jdbcTemplate
                .query(query, rowMapper, email)
                .stream()
                .findFirst();
    }
}
