package com.UKG.IJPmicroservices.Model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    private String description;

    @OneToMany(mappedBy = "opening", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ApplicationsModel> applications;
}
