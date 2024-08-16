package com.UKG.IJPmicroservices.Controller;

import com.UKG.IJPmicroservices.Model.OpeningModel;
import com.UKG.IJPmicroservices.Services.OpeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/openings")
public class OpeningController {

    @Autowired
    private OpeningService openingService;

    // Get all openings
    @GetMapping("/all")
    public ResponseEntity<List<OpeningModel>> getAllOpenings() {
        return openingService.getAllOpenings();
    }

    // Get opening by ID
    @GetMapping("/{id}")
    public ResponseEntity<OpeningModel> getOpeningById(@PathVariable Long id) {
        return openingService.getOpeningById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
    @PutMapping("/{id}")
    public ResponseEntity<OpeningModel> updateOpening(@PathVariable Long id, @RequestBody OpeningModel updatedOpening) {
        try {
            OpeningModel opening = openingService.updateOpening(id, updatedOpening);
            return ResponseEntity.ok(opening);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOpening(@PathVariable Long id) {
        openingService.deleteOpening(id);
        return ResponseEntity.noContent().build();
    }
}
