package com.hiithatcher.hiithatcherapi.controllers;

import com.hiithatcher.hiithatcherapi.models.Exercise;
import com.hiithatcher.hiithatcherapi.services.ExercisesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExercisesController {

    private final ExercisesService service;

    public ExercisesController(ExercisesService service) { this.service = service; }

    // "/"

    @PostMapping("/")
    public Exercise createExercise(@RequestBody Exercise exercise) { return service.createSingleExercise(exercise); }

    @GetMapping("/")
    public List<Exercise> readAllExercises() { return service.readAllExercises(); }

    // "/{name}"

    @GetMapping("/{name}")
    public Exercise getExerciseByName(@PathVariable String name) { return service.readSingleExercise(name); }

    @DeleteMapping("/{name}")
    public void deleteExerciseByName(@PathVariable String name) { service.deleteSingleExercise(name); }
}
