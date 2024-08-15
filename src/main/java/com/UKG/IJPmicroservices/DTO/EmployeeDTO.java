package com.UKG.IJPmicroservices.DTO;

import lombok.Data;
import java.util.List;

@Data
public class EmployeeDTO {
    private Long empID;
    private String empName;
    private String empRole;
    private Boolean isAdmin;
    private String username;
    private String password;
    private List<ApplicationDTO> applications;
}
