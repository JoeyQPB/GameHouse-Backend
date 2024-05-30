package com.joey.gamehousesendEmailservice.usecases.DTO;

public record EmailRequestDTO (String to, String subject, String body) {

    @Override
    public String toString() {
        return "EmailRequestDTO [to=" + to + ", subject=" + subject + ", body=" + body + "]";
    }
}
