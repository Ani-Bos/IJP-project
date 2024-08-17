package com.UKG.IJPmicroservices.Services;

import com.UKG.IJPmicroservices.DTO.OpeningDTO;
import com.UKG.IJPmicroservices.Model.JobModel;
import com.UKG.IJPmicroservices.Model.OpeningModel;
import com.UKG.IJPmicroservices.Repository.JobRepository;
import com.UKG.IJPmicroservices.Repository.OpeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OpeningService {

    @Autowired
    private OpeningRepository openingRepository;

    @Autowired
    private JobRepository jobRepository;

    public OpeningDTO convertToDTO (OpeningModel opening){
        OpeningDTO dto = new OpeningDTO();
        dto.setOpeningId(opening.getOpeningId());
        dto.setDescription(opening.getDescription());
        return dto;
    }

    public List<OpeningDTO> getAllOpenings() {
        List<OpeningModel> openings = openingRepository.findAll();
        return openings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<OpeningModel> getOpeningById(Long openingId) {
        return openingRepository.findById(openingId);
    }

    public ResponseEntity<OpeningModel> addOpening(OpeningModel opening) {
        // Ensure job exists
        JobModel job = jobRepository.findById(opening.getJob().getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // Update job reference
        opening.setJob(job);

        // Save the opening model without applications
        return new ResponseEntity<>(openingRepository.save(opening),HttpStatus.CREATED);
    }

    public Optional<OpeningModel> updateOpening(Long openingId, OpeningModel updatedOpening) {
        return Optional.ofNullable(openingRepository.findById(openingId).map(opening -> {
            opening.setDescription(updatedOpening.getDescription() != null ? updatedOpening.getDescription() : opening.getDescription());
            opening.setJob(updatedOpening.getJob() != null ? updatedOpening.getJob() : opening.getJob());
            opening.setLastDateToApply(updatedOpening.getLastDateToApply() != null ? updatedOpening.getLastDateToApply() : opening.getLastDateToApply());
            opening.setLocation(updatedOpening.getLocation() != null ? updatedOpening.getLocation() : opening.getLocation());
            opening.setTitle(updatedOpening.getTitle() != null ? updatedOpening.getTitle() : opening.getTitle());
            opening.setSkills(updatedOpening.getSkills() != null ? updatedOpening.getSkills() : opening.getSkills());
            opening.setSalary(updatedOpening.getSalary() != null ? updatedOpening.getSalary() : opening.getSalary());

            return openingRepository.save(opening);
        }).orElseThrow(() -> new RuntimeException("Opening not found with id " + openingId)));
    }


    public void deleteOpening(Long openingId) {
        openingRepository.deleteById(openingId);
    }
}
