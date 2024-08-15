package com.UKG.IJPmicroservices.Services;

import com.UKG.IJPmicroservices.DTO.JobDTO;
import com.UKG.IJPmicroservices.Exceptions.EmployeeExceptions;
import com.UKG.IJPmicroservices.Exceptions.JobExceptions;
import com.UKG.IJPmicroservices.Model.JobModel;
import com.UKG.IJPmicroservices.Model.OpeningModel;
import com.UKG.IJPmicroservices.Repository.JobRepository;
import com.UKG.IJPmicroservices.Repository.OpeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class JobServices {
   @Autowired
    private JobRepository jobrepo;
   @Autowired
    private OpeningRepository openingrepo;
    public JobModel addJobs(@RequestBody JobDTO req){
        if (jobrepo.findByJobId(req.getJobId()).isPresent()){
            throw  new JobExceptions("job already exist");
        }
       JobModel job = new JobModel();
       job.setDescription(req.getDescription());
       job.setSalary(req.getSalary());
       jobrepo.save(job);
       OpeningModel opening = new OpeningModel();
        opening = openingrepo.save(opening);
       opening.setJob(job);
       return job;
   }
   public List<JobModel>getAllJobs(){
       if(jobrepo.findAll().size()==0){
           throw new JobExceptions("No jobs exist");
       }
       return jobrepo.findAll();
   }

    public JobModel deleteJobByID(@PathVariable("id") long id){
        if(jobrepo.findById(id).isEmpty()){
            throw new JobExceptions("id not found");
        }
        JobModel job1 = jobrepo.findByJobId(id).get();
        jobrepo.deleteById(id);
        return job1;
    }

}
