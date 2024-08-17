package com.UKG.IJPmicroservices.Services;

import com.UKG.IJPmicroservices.Model.EmployeeModel;
import com.UKG.IJPmicroservices.Repository.EmployeeRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NotificationConsumer {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmployeeRepository employeeRepository;

//    @RabbitListener(queues = "${rabbitmq.queue.name}")
@RabbitListener(queues = "email_queue")
    public void consumeMessage(String message){
        System.out.println("Recieved message is" + message);
        List<EmployeeModel> admins = employeeRepository.findByIsAdmin(true);
        for(EmployeeModel x: admins){
            System.out.println("email is " + x.getUsername());
            emailService.sendEmail(x.getUsername(),"New Job Application" , message);
        }
    }
}
