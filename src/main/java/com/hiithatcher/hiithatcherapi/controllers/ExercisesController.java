package com.hiithatcher.hiithatcherapi.controllers;

import com.hiithatcher.hiithatcherapi.models.Exercise;
import com.hiithatcher.hiithatcherapi.repositories.ExercisesRepository;
import com.hiithatcher.hiithatcherapi.services.ExercisesService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExercisesController {

    private final ExercisesService service;
    private final ExercisesRepository repository;

    public ExercisesController(ExercisesService service, ExercisesRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    // "/"

    @PostMapping("/")
    public Exercise createExercise(@RequestBody Exercise exercise) {
        exercise.set_id(ObjectId.get());
        repository.save(exercise);
        return exercise;
    }

    @GetMapping("/")
    public List<Exercise> readAllExercises() {
        return service.readAllExercises();
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
