package com.UKG.IJPmicroservices.Repositories;

import com.UKG.IJPmicroservices.Model.ApplicationsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationsInterface extends JpaRepository<ApplicationsModel, Long> {
}
