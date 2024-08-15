package com.UKG.IJPmicroservices.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Employee_Table")
public class EmployeeModel {
    @Id
    @GeneratedValue
    private long empID;
    private String empName;
    private String empRole;
    private Boolean isAdmin;

    private String username;
    private String password;

    @OneToMany
    private List<ApplicationsModel> applications;
}
