package com.UKG.IJPmicroservices.Controller;

import com.UKG.IJPmicroservices.Exceptions.EmployeeExceptions;
import com.UKG.IJPmicroservices.Model.EmployeeModel;
import com.UKG.IJPmicroservices.Repository.EmployeeRepository;
import com.UKG.IJPmicroservices.Services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeRepository emprepo;
    @Autowired
    EmployeeServices serv1;
     @GetMapping(path="/all")
     public ResponseEntity<List<EmployeeModel>> getAll(){
         try{
             return new ResponseEntity<List<EmployeeModel>>(serv1.getAllEmployees(), HttpStatus.OK);
         }
         catch(EmployeeExceptions e){
             return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
         }
     }
     @GetMapping(path="/get/{id}")
    public ResponseEntity<EmployeeModel>getEmployeeByID(@PathVariable("id") long id){
         try{
             return new ResponseEntity<EmployeeModel>(serv1.getEmployeebyID(id),HttpStatus.OK);
         }
         catch(EmployeeExceptions e){
             return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
         }
     }
}
