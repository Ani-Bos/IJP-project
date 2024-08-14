package com.UKG.IJPmicroservices.Repositories;

import com.UKG.IJPmicroservices.Model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeInterface extends JpaRepository<EmployeeModel, Long> {
}
