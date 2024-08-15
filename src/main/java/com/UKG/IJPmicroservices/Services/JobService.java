package com.UKG.IJPmicroservices.Services;

import com.UKG.IJPmicroservices.Model.JobModel;
import com.UKG.IJPmicroservices.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public JobModel addJob(JobModel jobModel) {
        try{
            return jobRepository.save(jobModel);
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            return jobModel;
        }
    }
}
