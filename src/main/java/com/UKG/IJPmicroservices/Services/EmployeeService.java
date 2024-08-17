package com.UKG.IJPmicroservices.Services;

import com.UKG.IJPmicroservices.DTO.ApplicationDTO;
import com.UKG.IJPmicroservices.DTO.EmployeeDTO;
import com.UKG.IJPmicroservices.Exceptions.EmployeeExceptions;
import com.UKG.IJPmicroservices.Model.ApplicationsModel;
import com.UKG.IJPmicroservices.Model.EmployeeModel;
import com.UKG.IJPmicroservices.Model.OpeningModel;
import com.UKG.IJPmicroservices.Repository.ApplicationRepository;
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
    private ApplicationRepository applicationRepository;

    @Autowired
    private EmailService emailService;

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
        applicationRepository.save(application);
        String message = "Employee " + employee.getEmpName() + " with EmployeeID " + employee.getEmpID() +
                " has applied for Job Opening: " + opening.getDescription();
        List<EmployeeModel> admins = employeeRepository.findByIsAdmin(true);
        for (EmployeeModel admin : admins) {
            System.out.println("email sent to admin emails like " + admin.getUsername());
            emailService.sendEmail(admin.getUsername(), "New Job Application", message);
        }
        return ResponseEntity.ok("Application submitted successfully and notification sent.");
    }

    public EmployeeModel addEmployee(EmployeeModel employee) {
        // Save the employee without applications
        return employeeRepository.save(employee);
    }

    public Optional<EmployeeModel> getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public List<EmployeeModel>getAllEmployees(){
        if(employeeRepository.findAll().size()==0){
            throw new EmployeeExceptions("List is empty");
        }
        return employeeRepository.findAll();
    }

    public List<ApplicationDTO> getEmployeeApplications(Long empId) {
        EmployeeModel employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        List<ApplicationDTO> applicationDTOs = employee.getApplications().stream()
                .map(app -> {
                    ApplicationDTO applicationDTO = new ApplicationDTO();
                    applicationDTO.setApplicationId(app.getApplicationId());
                    applicationDTO.setOpeningId(app.getOpening().getOpeningId());
                    applicationDTO.setEmpId(app.getEmployee().getEmpID());
                    applicationDTO.setEmpName(app.getEmployee().getEmpName());
                    applicationDTO.setEmpRole(app.getEmployee().getEmpRole());
                    return applicationDTO;
                }).toList();

        return applicationDTOs;
    }
}
