package com.hiithatcher.hiithatcherapi.controllers;

import com.hiithatcher.hiithatcherapi.models.Exercise;
import com.hiithatcher.hiithatcherapi.repositories.ExercisesRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExercisesController {

    @Autowired
    private ExercisesRepository repository;

    @GetMapping("/")
    public List<Exercise> getAllExercises() {
        System.out.println(repository.findAll());
        return repository.findAll();
    }

    @GetMapping("/{name}")
    public Exercise getExerciseByName(@PathVariable String name) {
        return repository.findByName(name);
    }

    @PostMapping("/")
    public Exercise createExercise(@RequestBody Exercise exercise) {
        exercise.set_id(ObjectId.get());
        repository.save(exercise);
        return exercise;
    }
}
