package com.UKG.IJPmicroservices.Controller;

import com.UKG.IJPmicroservices.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmailTestController {
    @Autowired
    private EmailService emailService;
    @PostMapping("/test")
    public ResponseEntity<String> testEmail() {
        emailService.sendEmail("email@gmail.com", "Test Subject", "This is a test email.");
        return ResponseEntity.ok("Test email sent.");
    }
}
