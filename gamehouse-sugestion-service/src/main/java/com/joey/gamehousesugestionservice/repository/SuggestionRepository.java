package com.joey.gamehousesugestionservice.repository;

import com.joey.gamehousesugestionservice.domain.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
    List<Suggestion> findAllByEmail(String email);
}
