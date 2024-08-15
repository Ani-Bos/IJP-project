package com.UKG.IJPmicroservices.DTO;

import lombok.Data;

@Data
public class RegisterDTO {
    private String empName;
    private String empRole;  // changed EmpRole to empRole
    private Boolean isAdmin;

    private String username;
    private String password;
}
