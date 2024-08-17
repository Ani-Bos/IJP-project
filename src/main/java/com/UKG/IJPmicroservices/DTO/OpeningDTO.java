package com.UKG.IJPmicroservices.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OpeningDTO {
    private Long openingId;
    private String description;
    private String Location;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMM yyyy")
    private Date lastDateToApply;
    private String title;
    private String skills;
    private String Salary;
}
