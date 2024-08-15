package com.UKG.IJPmicroservices.Services;

import com.UKG.IJPmicroservices.Exceptions.CustomUserDetailsException;
import com.UKG.IJPmicroservices.Model.EmployeeModel;
import com.UKG.IJPmicroservices.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<EmployeeModel> employee = employeeRepo.findByUsername(username);
        if (employee.isEmpty()) {
            throw new CustomUserDetailsException("Employee not found");
        }
        return new org.springframework.security.core.userdetails.User(
                employee.get().getUsername(),
                employee.get().getPassword(),
                new ArrayList<>()
        );
    }
}
