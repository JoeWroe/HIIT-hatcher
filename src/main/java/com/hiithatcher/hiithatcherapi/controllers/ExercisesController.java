package com.hiithatcher.hiithatcherapi.controllers;

import com.hiithatcher.hiithatcherapi.models.Exercise;
import com.hiithatcher.hiithatcherapi.repositories.ExercisesRepository;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExercisesController {

    private final ExercisesRepository repository;

    public ExercisesController(ExercisesRepository repository) {
        this.repository = repository;
    }

    // "/"

    @GetMapping("/")
    public List<Exercise> getAllExercises() {
        System.out.println(repository.findAll());
        return repository.findAll();
    }

    @PostMapping("/")
    public Exercise createExercise(@RequestBody Exercise exercise) {
        exercise.set_id(ObjectId.get());
        repository.save(exercise);
        return exercise;
    }

    // "/{name}"

    @GetMapping("/{name}")
    public Exercise getExerciseByName(@PathVariable String name) {
        return repository.findByName(name);
    }

    @DeleteMapping("/{name}")
    public void deleteExerciseByName(@PathVariable String name) {
        repository.delete(repository.findByName(name));
    }
}
