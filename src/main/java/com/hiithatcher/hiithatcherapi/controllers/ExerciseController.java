package com.hiithatcher.hiithatcherapi.controllers;

import com.hiithatcher.hiithatcherapi.models.Exercise;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ExerciseController {

    @GetMapping("/exercise/{name}")
    public Exercise exercise(@PathVariable String name) {
        return new Exercise(name);
    }
}
