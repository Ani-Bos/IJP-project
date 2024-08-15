package com.UKG.IJPmicroservices.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="Opening Table")
public class OpeningModel {
    @Id
    @GeneratedValue
    private long openingId;
    @OneToOne
    @JoinColumn
    private JobModel job;
    @OneToMany
    private List<ApplicationsModel> Applications;
}
