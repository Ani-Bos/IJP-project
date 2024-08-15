package com.UKG.IJPmicroservices.Services;

import com.UKG.IJPmicroservices.DTO.ApplicationDTO;
import com.UKG.IJPmicroservices.DTO.EmployeeDTO;
import com.UKG.IJPmicroservices.Model.ApplicationsModel;
import com.UKG.IJPmicroservices.Model.EmployeeModel;
import com.UKG.IJPmicroservices.Model.OpeningModel;
import com.UKG.IJPmicroservices.Repository.ApplicationsRepository;
import com.UKG.IJPmicroservices.Repository.EmployeeRepository;
import com.UKG.IJPmicroservices.Repository.OpeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OpeningRepository openingRepository;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    public EmployeeDTO convertToDTO(EmployeeModel employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmpID(employee.getEmpID());
        dto.setEmpName(employee.getEmpName());
        dto.setEmpRole(employee.getEmpRole());
        dto.setIsAdmin(employee.getIsAdmin());
        dto.setUsername(employee.getUsername());
        dto.setPassword(employee.getPassword());

        List<ApplicationDTO> applicationDTOs = employee.getApplications().stream()
                .map(app -> {
                    ApplicationDTO applicationDTO = new ApplicationDTO();
                    applicationDTO.setApplicationId(app.getApplicationId());
                    applicationDTO.setOpeningId(app.getOpening().getOpeningId());
                    return applicationDTO;
                }).toList();

        dto.setApplications(applicationDTOs);
        return dto;
    }

    public ResponseEntity<String> applyForOpening(Long employeeId, Long openingId) {
        // Validate if employee exists
        EmployeeModel employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Validate if opening exists
        OpeningModel opening = openingRepository.findById(openingId)
                .orElseThrow(() -> new RuntimeException("Opening not found"));

        // Create and save the application
        ApplicationsModel application = new ApplicationsModel();
        application.setEmployee(employee);
        application.setOpening(opening);

        applicationsRepository.save(application);

        return ResponseEntity.ok("Application submitted successfully.");
    }

    public EmployeeModel addEmployee(EmployeeModel employee) {
        // Save the employee without applications
        return employeeRepository.save(employee);
    }

    public Optional<EmployeeModel> getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }
}
