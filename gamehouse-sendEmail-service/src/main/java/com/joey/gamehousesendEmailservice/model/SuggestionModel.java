package com.joey.gamehousesendEmailservice.model;

public class SuggestionModel {

    private Long id;
    private String email;
    private String suggestion;
    private Boolean consumed;

    public SuggestionModel () {};

    public SuggestionModel(Long id, String email, String suggestion, Boolean consumed) {
        this.id = id;
        this.email = email;
        this.suggestion = suggestion;
        this.consumed = consumed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public Boolean getConsumed() {
        return consumed;
    }

    public void setConsumed(Boolean consumed) {
        this.consumed = consumed;
    }

    @Override
    public String toString() {
        return "SuggestionModel{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", suggestion='" + suggestion + '\'' +
                ", consumed=" + consumed +
                '}';
    }
}