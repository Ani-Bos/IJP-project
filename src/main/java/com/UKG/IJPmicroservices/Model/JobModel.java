package com.UKG.IJPmicroservices.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="Job Table")
public class JobModel {
    @Id
    @GeneratedValue
    private long JobId;
    private double salary;
    @OneToMany
    private List<OpeningModel> openings;
}
