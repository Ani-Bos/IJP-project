package com.UKG.IJPmicroservices.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    public void sendEmail(String toEmail,String subject, String body){
       try{
           SimpleMailMessage message = new SimpleMailMessage();
           message.setFrom("aniketbose47@gmail.com");
           message.setTo(toEmail);
           message.setText(body);
           message.setSubject(subject);
           mailSender.send(message);
           System.out.println("Email Sent successfully");
       }
       catch(MailException e){
           System.out.println("Not able to send email");
           System.out.println(e.getMessage());
       }
    }
}
