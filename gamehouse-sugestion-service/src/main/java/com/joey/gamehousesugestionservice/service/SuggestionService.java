package com.joey.gamehousesugestionservice.service;

import com.joey.gamehousesugestionservice.domain.Suggestion;
import com.joey.gamehousesugestionservice.domain.records.SuggestionRequestDTO;
import com.joey.gamehousesugestionservice.exceptions.EmptyFieldsExceptions;
import com.joey.gamehousesugestionservice.exceptions.SuggestionNotFoundException;
import com.joey.gamehousesugestionservice.repository.SuggestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionService {

    @Autowired
    private SuggestionRepository suggestionRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(SuggestionService.class);

    public Suggestion create(SuggestionRequestDTO suggestionDto) {
        if (suggestionDto.suggestion().isEmpty() || suggestionDto.email().isEmpty()) {
            throw new EmptyFieldsExceptions("Suggestions request must have suggestion and email: " + suggestionDto);
        }

        Suggestion suggestion = this.suggestionRepository.save(new Suggestion(suggestionDto));
        LOGGER.info("[:] Created: " + suggestion);
        return suggestion;
    }

    public Suggestion findById(Long id) {
        Suggestion suggestion = this.suggestionRepository.findById(id)
                .orElseThrow(() -> new SuggestionNotFoundException("Not found Suggestion for id:" + id));
        LOGGER.info("[:] Found by id: " + suggestion);
        return suggestion;
    }

    public List<Suggestion> findAll() {
        LOGGER.info("[:] Finding all suggestions");
        return this.suggestionRepository.findAll();
    }

    public Suggestion update(Long id, Boolean consumed) {
        Suggestion suggestion = this.findById(id);
        suggestion.setConsumed(consumed);
        Suggestion suggestionUpdated = this.suggestionRepository.save(suggestion);
        LOGGER.info("[:] Updated Suggestion: " + suggestionUpdated);
        return suggestionUpdated;
    }

    public Boolean delete(Long id) {
        Suggestion suggestion = this.findById(id);
        this.suggestionRepository.delete(suggestion);
        LOGGER.info("[:] Deleted Suggestion id: " + id);
        return true;
    }
}
