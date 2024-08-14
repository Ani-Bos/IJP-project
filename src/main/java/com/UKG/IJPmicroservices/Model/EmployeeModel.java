package com.UKG.IJPmicroservices.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Employee Table")
public class EmployeeModel {
    @Id
    @GeneratedValue
    private long EmpID;
    private String EmpName;
    private String EmpRole;
    @OneToMany
    private List<ApplicationsModel> applications;
}
