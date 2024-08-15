package com.UKG.IJPmicroservices.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="Job_Table")
public class JobModel {
    @Id
    @GeneratedValue
    private long jobId;
    private String description;
    private double salary;
    @OneToOne
    private OpeningModel openings;
}
