package com.UKG.IJPmicroservices.DTO;

import lombok.Data;

@Data
public class JobDTO {
    private Long jobId;
    private String description;
    private double salary;
}
