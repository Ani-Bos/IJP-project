package com.UKG.IJPmicroservices.Controller;

import com.UKG.IJPmicroservices.DTO.ApplicationDTO;
import com.UKG.IJPmicroservices.Model.ApplicationsModel;
import com.UKG.IJPmicroservices.Model.EmployeeModel;
import com.UKG.IJPmicroservices.Services.ApplicationService;
import com.UKG.IJPmicroservices.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private EmployeeService employeeService;

    // Get all applications (for HR/Admin)
    @GetMapping("/all")
    public ResponseEntity<List<ApplicationDTO>> getAllApplications(@RequestParam(required = false) String role) {
        if (role != null && role.equalsIgnoreCase("hr")) {
            return ResponseEntity.ok(applicationService.getAllApplications());
        } else {
            return ResponseEntity.status(403).build(); // Forbidden for non-HR users
        }
    }

    // Get all applications for a specific opening (Only for HR/Admin)
    @GetMapping("/opening/{openingId}")
    public ResponseEntity<List<ApplicationDTO>> getAllApplicationsForOpening(
            @PathVariable Long openingId,
            @RequestParam("role") String role) {

        if (!"hr".equalsIgnoreCase(role)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<ApplicationDTO> applications = applicationService.getAllApplicationsForOpening(openingId);
        return ResponseEntity.ok(applications);
    }

    // Get all applications for a specific employee
    @GetMapping("/employee/{empID}")
    public ResponseEntity<List<ApplicationsModel>> getApplicationsByEmployee(@PathVariable Long empID) {
        EmployeeModel employee = employeeService.getEmployeeById(empID)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return ResponseEntity.ok(applicationService.getApplicationsByEmployee(employee));
    }
}
