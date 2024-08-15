package com.UKG.IJPmicroservices.Controller;

import com.UKG.IJPmicroservices.DTO.LoginDTO;
import com.UKG.IJPmicroservices.DTO.RegisterDTO;
import com.UKG.IJPmicroservices.Exceptions.AuthenticationException;
import com.UKG.IJPmicroservices.Model.EmployeeModel;
import com.UKG.IJPmicroservices.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthService authService;
    @PostMapping(path="/register")
    public ResponseEntity<EmployeeModel> register(@RequestBody RegisterDTO req){
        try{
            return new ResponseEntity(authService.register(req),HttpStatus.CREATED);

        }
        catch(AuthenticationException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping(path="/login")
    public ResponseEntity<EmployeeModel> login(@RequestBody LoginDTO req){
        try{
            return new ResponseEntity<>(authService.login(req),HttpStatus.FOUND);
        }
        catch(AuthenticationException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
