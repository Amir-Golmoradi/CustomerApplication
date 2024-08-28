package dev.amir.masterclass.infrastructure.security;

import dev.amir.masterclass.infrastructure.Dao.interfaces.customer.ICustomerDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ICustomerDao customerDao;

    public UserDetailsServiceImpl(ICustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerDao.selectCustomerByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("Username " + username + " not found")
        );
    }
}
