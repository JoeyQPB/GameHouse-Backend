package com.joey.gamehousesendEmailservice.infra.ses;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.joey.gamehousesendEmailservice.adpters.IEmailSenderGateway;
import com.joey.gamehousesendEmailservice.exceptions.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

@Service
public class SesEmailSender implements IEmailSenderGateway {

    @Autowired
    private AmazonSimpleEmailService amazonSimpleEmailService;

    @Override
    public void sendEmail(String to, String subject, String body) {
        Destination destination = new Destination().withToAddresses(to);
        Content subjectContent = new Content().withData(subject);
        Content bodyContent = new Content().withData(body);
        Message message = new Message().withSubject(subjectContent).withBody(new Body().withText(bodyContent));

        SendEmailRequest request = new SendEmailRequest()
                .withSource("gamehouseproject00@gmail.com")
                .withDestination(destination)
                .withMessage(message);

        try {
            this.amazonSimpleEmailService.sendEmail(request);
        } catch (AmazonServiceException e) {
            throw new EmailServiceException("Error while sent email!", e.getMessage());
        }
    }

}
