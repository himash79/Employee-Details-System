package lk.himash.employeeservice.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lk.himash.employeeservice.model.EmployeeMaster;
import lk.himash.employeeservice.repository.EmployeeMasterRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private EmployeeMasterRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeMaster user = repository.findByUserName(username);
        return new org.springframework.security.core.userdetails.User(user.getEmp_Username(), user.getEmp_Password(), new ArrayList<>());
    }
}
