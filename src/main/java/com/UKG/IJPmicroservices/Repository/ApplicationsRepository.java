package com.UKG.IJPmicroservices.Repository;

import com.UKG.IJPmicroservices.Model.ApplicationsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ApplicationsRepository extends JpaRepository<ApplicationsModel, Long> {
}
