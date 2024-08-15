package com.UKG.IJPmicroservices.Repository;

import com.UKG.IJPmicroservices.Model.JobModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<JobModel, Long> {
}
