package dev.amir.masterclass.infrastructure.Dao.implementations.customerImpl;

import com.github.javafaker.Faker;
import dev.amir.masterclass.domain.entity.Customer;
import dev.amir.masterclass.infrastructure.Dao.interfaces.customer.ICustomerDao;
import dev.amir.masterclass.infrastructure.repository.ICustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Repository("list")
public class CustomerListDataAccessService implements ICustomerDao {

    // db
    private static final List<Customer> customers = List.of();

//    static {
//        customers = new ArrayList<>();
//        var faker = new Faker();
//        var fakeFirstName = faker.name().firstName();
//        var fakeLastName = faker.name().lastName();
//        var fakeEmail = fakeFirstName + "." + fakeLastName;
//        var fakePassword = faker.internet().password();
//        var fakeId = faker.number().randomDigitNotZero();
//        Random random = new Random();
//        Customer alex = new Customer(
//                fakeId,
//                fakeFirstName,
//                fakeEmail,
//                fakePassword,
//                random.nextInt(16, 99)
//        );
//        customers.add(alex);
//
//
//    }

    @Bean
    CommandLineRunner runner(ICustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            var faker = new Faker();
            var name = faker.name();
            String firstName = name.firstName();
            String lastName = name.lastName();
            var fakeId = faker.number();
            var fakeEmail = faker.internet().safeEmailAddress();

            Random random = new Random();
            Customer customer = new Customer(
                    fakeId.numberBetween(1, customers.toArray().length),
                    firstName + " " + lastName,
                    firstName.toLowerCase() + "." + lastName.toLowerCase() + "@amirgolmoradi",
                    passwordEncoder.encode(UUID.randomUUID().toString()),
                    random.nextInt(16, 99)
            );
            customerRepository.save(customer);
        };
    }

    @Override
    public List<Customer> selectAllCustomer() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existsCustomerWithEmail(String email) {
        return customers.stream()
                .anyMatch(c -> c.getEmail().equals(email));
    }

    @Override
    public void deleteCustomer(Customer customer) {

    }

    @Override
    public boolean existsCustomerById(Integer id) {
        return customers.stream()
                .anyMatch(c -> c.getId().equals(id));
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        customers.stream()
                .filter(c -> c.getId().equals(customerId))
                .findFirst()
                .ifPresent(customers::remove);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public Optional<Customer> selectCustomerByEmail(String email) {
        return customers.stream()
                .filter(c -> c.getUsername().equals(email))
                .findFirst();
    }
}