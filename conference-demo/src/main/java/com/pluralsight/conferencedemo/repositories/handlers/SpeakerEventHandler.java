package com.pluralsight.conferencedemo.repositories.handlers;

import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.repositories.SpeakerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.util.HashSet;

@Component
@RepositoryEventHandler
public class SpeakerEventHandler {

    @Autowired
    SpeakerJpaRepository repository;

    @HandleBeforeCreate
    public void handleSpeakerCreate(Speaker speaker) {
        //We want to enforce unique speaker last names
        Speaker foundSpeaker = repository.findFirstByFirstName(speaker.getFirstName());
        if (foundSpeaker != null){
            System.out.println("First name needs to be unique");
            throw new ConstraintViolationException("First name need to be unique",new HashSet<>());
        }
    }
}
