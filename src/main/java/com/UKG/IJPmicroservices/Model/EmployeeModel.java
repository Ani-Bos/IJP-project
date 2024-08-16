package com.UKG.IJPmicroservices.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "employee")
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empID;
    private String empName;
    private String empRole;
    private Boolean isAdmin;

    private String username;
    private String password;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ApplicationsModel> applications;

}
