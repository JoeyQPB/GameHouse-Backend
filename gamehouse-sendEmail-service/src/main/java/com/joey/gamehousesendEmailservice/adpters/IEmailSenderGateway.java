package com.joey.gamehousesendEmailservice.adpters;

public interface  IEmailSenderGateway {
    void sendEmail(String to, String subject, String body);
}
