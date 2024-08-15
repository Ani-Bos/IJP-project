package com.UKG.IJPmicroservices.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Applications_Table")
public class ApplicationsModel {
    @Id
    @GeneratedValue
    private  long ApplicationId;
    @ManyToOne
    @JoinColumn
    private EmployeeModel Employee;
    @ManyToOne
    @JoinColumn
    private OpeningModel Opening;
}
