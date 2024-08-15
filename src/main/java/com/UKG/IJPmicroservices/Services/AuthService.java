package com.UKG.IJPmicroservices.Services;

import com.UKG.IJPmicroservices.DTO.LoginDTO;
import com.UKG.IJPmicroservices.DTO.RegisterDTO;
import com.UKG.IJPmicroservices.Exceptions.AuthenticationException;
import com.UKG.IJPmicroservices.Model.EmployeeModel;
import com.UKG.IJPmicroservices.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private EmployeeRepository emprepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public EmployeeModel register(RegisterDTO req){
        EmployeeModel emp = new EmployeeModel();
        emp.setEmpName(req.getEmpName());
        emp.setEmpRole(req.getEmpRole());
        emp.setIsAdmin(req.getIsAdmin());
        emp.setUsername(req.getUsername());
        emp.setPassword(passwordEncoder.encode(req.getPassword()));
        return emprepo.save(emp);
    }
    public EmployeeModel login(LoginDTO req){
        Optional<EmployeeModel> emp = emprepo.findByUsername(req.getUsername());
        System.out.println("employee is ");
        System.out.println(emp);
        if(emp.isEmpty() ||
                (!passwordEncoder.matches(req.getPassword(),emp.get().getPassword()))){
            throw new AuthenticationException("Invalid Credentials");
        }
        return emp.get();
    }
}
