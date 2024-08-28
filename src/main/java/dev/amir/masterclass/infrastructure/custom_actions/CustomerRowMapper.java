package dev.amir.masterclass.infrastructure.custom_actions;

import dev.amir.masterclass.domain.entity.Customer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Customer(
                rs.getInt("customer_id"),
                rs.getString("customer_name"),
                rs.getString("customer_email"),
                rs.getString("customer_password"),
                rs.getInt("customer_age")
        );
    }
}
