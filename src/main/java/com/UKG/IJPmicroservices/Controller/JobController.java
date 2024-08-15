package com.UKG.IJPmicroservices.Controller;

import com.UKG.IJPmicroservices.DTO.JobDTO;
import com.UKG.IJPmicroservices.Exceptions.JobExceptions;
import com.UKG.IJPmicroservices.Model.JobModel;
import com.UKG.IJPmicroservices.Model.OpeningModel;
import com.UKG.IJPmicroservices.Services.JobServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {
    @Autowired
    JobServices jobserv;
    @PostMapping(path="/addJobs")
    public ResponseEntity<JobModel> addJobs(@RequestBody JobDTO req){
       try{
           return new ResponseEntity<JobModel>(jobserv.addJobs(req),HttpStatus.OK);
       }
       catch (JobExceptions e){
           return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
       }
    }
    @GetMapping("/alljobs")
    public ResponseEntity<List<JobModel>>getAllJobs(){
        try {
            return  new ResponseEntity<>(jobserv.getAllJobs(),HttpStatus.OK);
        }
        catch (JobExceptions e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/deletejobs/{id}")
    public ResponseEntity<JobModel> deleteJobsbyID(@PathVariable("id") long id){
        try{
            return new ResponseEntity<JobModel>(jobserv.deleteJobByID(id),HttpStatus.OK);
        }
        catch(JobExceptions e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
