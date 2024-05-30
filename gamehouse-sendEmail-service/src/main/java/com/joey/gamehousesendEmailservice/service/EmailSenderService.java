package com.joey.gamehousesendEmailservice.service;

import com.joey.gamehousesendEmailservice.adpters.IEmailSenderGateway;
import com.joey.gamehousesendEmailservice.usecases.IEmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService implements IEmailSenderService {

    @Autowired
    private IEmailSenderGateway emailSenderGateway;

    @Override
    public void sendEmail(String to, String subject, String body) {
        this.emailSenderGateway.sendEmail(to, subject, body);
    }
}
