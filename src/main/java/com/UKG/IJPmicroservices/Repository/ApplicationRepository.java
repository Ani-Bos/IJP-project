package com.UKG.IJPmicroservices.Repository;

import com.UKG.IJPmicroservices.Model.ApplicationsModel;
import com.UKG.IJPmicroservices.Model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ApplicationRepository extends JpaRepository<ApplicationsModel, Long> {
    List<ApplicationsModel> findByEmployee(EmployeeModel employee);
    List<ApplicationsModel> findByOpening_OpeningId(Long openingId);
}
