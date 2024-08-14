package com.UKG.IJPmicroservices.Repositories;

import com.UKG.IJPmicroservices.Model.JobModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobInterface extends JpaRepository<JobModel, Long> {
}
