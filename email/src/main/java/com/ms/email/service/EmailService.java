package com.ms.email.service;

import com.ms.email.dtos.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String mailFrom;

    public String sendEmail (EmailDto emailDto) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom(mailFrom);
        smm.setTo(emailDto.email());
        smm.setSubject("Welcome to Our Gaming Community!");
        smm.setText(this.buildMessageText(emailDto));

        try {
            javaMailSender.send(smm);
        }
        catch (MailException e) {
            return "!!! Error while sending email: " + e.getMessage();
        }

        return "Email sent!";
    }

    private String buildMessageText(EmailDto emailDto) {
        return "Dear " + emailDto.email().split("@")[0] + ",\n\n" +
                "Welcome to Game House, your gateway to endless gaming adventures! 🎮\n\n" +
                "We're thrilled to have you join our gaming community and embark on this exciting journey with us. As a registered member, you now have access to a plethora of games and exclusive features, including the ability to suggest new games to enhance our platform further.\n\n" +
                "Your registration details are as follows:\n" +
                "- Email: " + emailDto.email() + "\n" +
                "- Role(s): " + emailDto.role() + "\n" +
                "- User ID: " + emailDto.id() + "\n\n" +
                "Feel free to explore our wide range of games and engage with fellow gamers in our vibrant community. Whether you're a casual player or a hardcore enthusiast, there's something here for everyone.\n\n" +
                "If you have any questions, suggestions, or just want to say hello, don't hesitate to reach out to us. We're here to make your gaming experience unforgettable.\n\n" +
                "Once again, welcome aboard! Let the games begin!\n\n" +
                "Best regards,\n" +
                "Joey :p\n" +
                "From Game House!\n";
    }
}
