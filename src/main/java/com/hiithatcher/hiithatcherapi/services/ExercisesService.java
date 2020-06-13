package com.hiithatcher.hiithatcherapi.services;

import com.hiithatcher.hiithatcherapi.models.Exercise;
import com.hiithatcher.hiithatcherapi.repositories.ExercisesRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExercisesService {

    private final ExercisesRepository repository;

    public Exercise createSingleExercise(Exercise exercise) {
        exercise.set_id(ObjectId.get());
        repository.save(exercise);
        return exercise;
    }

    public List<Exercise> readAllExercises() {
        return repository.findAll();
    }

    public Exercise readSingleExercise(String exercise) {
        return repository.findByName(exercise);
    }

    public void deleteSingleExercise(String exercise) {
        repository.delete(repository.findByName(exercise));
    }
}
