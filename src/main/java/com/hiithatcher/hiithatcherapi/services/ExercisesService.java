package com.hiithatcher.hiithatcherapi.services;

import com.hiithatcher.hiithatcherapi.models.Exercise;
import com.hiithatcher.hiithatcherapi.repositories.ExercisesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExercisesService {

    private final ExercisesRepository repository;

    public List<Exercise> readAllExercises() {
        return repository.findAll();
    }
}
