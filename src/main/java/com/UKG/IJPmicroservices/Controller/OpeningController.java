package com.UKG.IJPmicroservices.Controller;

import com.UKG.IJPmicroservices.DTO.OpeningDTO;
import com.UKG.IJPmicroservices.Model.OpeningModel;
import com.UKG.IJPmicroservices.Services.OpeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/openings")
public class OpeningController {

    @Autowired
    private OpeningService openingService;

    // Get all openings
    @GetMapping("/all")
    public ResponseEntity<List<OpeningDTO>> getAllOpenings() {
        List<OpeningDTO> openingDTOs = openingService.getAllOpenings();
        return ResponseEntity.ok(openingDTOs);
    }

    // Get opening by ID
    @GetMapping("/{id}")
    public ResponseEntity<OpeningDTO> getOpeningById(@PathVariable Long id) {
        Optional<OpeningModel> opening = openingService.getOpeningById(id);
        return opening.map(o -> {
            OpeningDTO dto = new OpeningDTO();
            dto.setOpeningId(o.getOpeningId());
            dto.setDescription(o.getDescription());
            dto.setLastDateToApply(o.getLastDateToApply());
            dto.setLocation(o.getLocation());
            dto.setTitle(o.getTitle());
            dto.setSkills(o.getSkills());
            dto.setSalary(o.getSalary());
            return ResponseEntity.ok(dto);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Add a new opening
    @PostMapping("/add")
    public ResponseEntity<OpeningModel> addOpening(@RequestBody OpeningModel opening) {
        try {
            // Assuming jobId exists in the database, you can handle the job association here
            OpeningModel createdOpening = openingService.addOpening(opening).getBody();
            return new ResponseEntity<>(createdOpening, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Update an existing opening
    @PatchMapping("/update/{id}")
    public ResponseEntity<OpeningDTO> updateOpening(@PathVariable("id") Long id, @RequestBody OpeningModel updatedOpening) {
        try {
            Optional<OpeningModel> opening = openingService.updateOpening(id, updatedOpening);
            if (opening != null) {
                return opening.map(o -> {
                    OpeningDTO dto = new OpeningDTO();
                    dto.setOpeningId(o.getOpeningId());
                    dto.setDescription(o.getDescription());
                    dto.setLocation(o.getLocation());
                    dto.setLastDateToApply(o.getLastDateToApply());
                    dto.setTitle(o.getTitle());
                    dto.setSkills(o.getSkills());
                    dto.setSalary(o.getSalary());
                    return ResponseEntity.ok(dto);
                }).orElse(ResponseEntity.notFound().build());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOpening(@PathVariable Long id) {
        openingService.deleteOpening(id);
        return ResponseEntity.noContent().build();
    }
}
