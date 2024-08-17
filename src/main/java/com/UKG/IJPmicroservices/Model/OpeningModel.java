package com.UKG.IJPmicroservices.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="opening")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OpeningModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long openingId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private JobModel job;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMM yyyy")
    private Date lastDateToApply;

    private String Location;
    private String title;
    private String skills;
    private String description;
    private String Salary;

    @OneToMany(mappedBy = "opening", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ApplicationsModel> applications;
}
