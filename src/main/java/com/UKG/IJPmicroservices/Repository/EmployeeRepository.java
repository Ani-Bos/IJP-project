package com.UKG.IJPmicroservices.Repository;

import com.UKG.IJPmicroservices.Model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {
    Optional<EmployeeModel> findByEmpName(String empName);
    Optional<EmployeeModel> findByUsername(String username);
    List<EmployeeModel> findByIsAdmin(Boolean isAdmin);
}
