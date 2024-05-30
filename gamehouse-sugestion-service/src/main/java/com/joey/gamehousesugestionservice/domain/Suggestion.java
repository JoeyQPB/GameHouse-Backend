package com.joey.gamehousesugestionservice.domain;

import com.joey.gamehousesugestionservice.domain.records.SuggestionRequestDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name = "tb_suggestion")
public class Suggestion implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "suggestion", nullable = false)
    private String suggestion;

    @Column(name = "consumed", nullable = true)
    private Boolean consumed = false;

    public Suggestion() {}

    public Suggestion(Long id, String email, String suggestion, Boolean consumed) {
        this.id = id;
        this.email = email;
        this.suggestion = suggestion;
        this.consumed = consumed;
    }

    public Suggestion(String email, String suggestion) {
        this.email = email;
        this.suggestion = suggestion;
    }

    public Suggestion(SuggestionRequestDTO dto) {
        this.email = dto.email();
        this.suggestion = dto.suggestion();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Suggestion that = (Suggestion) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Suggestion{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", suggestion='" + suggestion + '\'' +
                ", consumed=" + consumed +
                '}';
    }
}
