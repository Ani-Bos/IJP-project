package com.UKG.IJPmicroservices.Services;

import com.UKG.IJPmicroservices.Exceptions.EmployeeExceptions;
import com.UKG.IJPmicroservices.Model.EmployeeModel;
import com.UKG.IJPmicroservices.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServices {
   @Autowired
    EmployeeRepository emprepo;
    public List<EmployeeModel> getAllEmployees(){
        if(emprepo.findAll().size()==0){
            throw new EmployeeExceptions("List is empty");
        }
        return emprepo.findAll();
    }
    public EmployeeModel getEmployeebyID(@PathVariable("id") long id){
        Optional<EmployeeModel> getEmployee = emprepo.findById(id);
        if(getEmployee.isEmpty()){
            throw new EmployeeExceptions("No Employee exist");
        }
        return getEmployee.get();
    }

}
