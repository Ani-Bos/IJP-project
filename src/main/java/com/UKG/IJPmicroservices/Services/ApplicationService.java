package com.UKG.IJPmicroservices.Services;

import com.UKG.IJPmicroservices.DTO.ApplicationDTO;
import com.UKG.IJPmicroservices.Model.ApplicationsModel;
import com.UKG.IJPmicroservices.Model.EmployeeModel;
import com.UKG.IJPmicroservices.Repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    // Fetch all applications (HR/Admin)
    public List<ApplicationDTO> getAllApplications() {
        List<ApplicationsModel>applications = applicationRepository.findAll();
        return applications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ApplicationDTO> getAllApplicationsForOpening(Long openingId) {
        List<ApplicationsModel> applications = applicationRepository.findByOpening_OpeningId(openingId);
        return applications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ApplicationDTO convertToDTO(ApplicationsModel application) {
        ApplicationDTO dto = new ApplicationDTO();
        dto.setApplicationId(application.getApplicationId());
        dto.setOpeningId(application.getOpening().getOpeningId());
        dto.setEmpId(application.getEmployee().getEmpID());
        dto.setEmpName(application.getEmployee().getEmpName());
        dto.setEmpRole(application.getEmployee().getEmpRole());
        return dto;
    }

    // Fetch applications for a specific employee
    public List<ApplicationsModel> getApplicationsByEmployee(EmployeeModel employee) {
        return applicationRepository.findByEmployee(employee);
    }
}
