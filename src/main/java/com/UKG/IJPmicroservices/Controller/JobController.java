package com.UKG.IJPmicroservices.Controller;

import com.UKG.IJPmicroservices.Model.JobModel;
import com.UKG.IJPmicroservices.Services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/add")
    public ResponseEntity<JobModel> addJob(@RequestBody JobModel jobModel) {
        try {
            JobModel createdJob = jobService.addJob(jobModel);
            return new ResponseEntity<>(createdJob, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
