package com.hiithatcher.hiithatcherapi.integration;

import com.hiithatcher.hiithatcherapi.controllers.ExercisesController;
import com.hiithatcher.hiithatcherapi.models.Exercise;
import com.hiithatcher.hiithatcherapi.repositories.ExercisesRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@ComponentScan("com.hiithatcher.hiithatcherapi")
public class ExercisesIntegrationTest {

    @Autowired
    private ExercisesController controller;

    @Autowired
    private ExercisesRepository repository;

    @AfterEach
    void teardown() {
        repository.deleteAll();
    }

    @Test
    public void newExercisesCanBeSaved() {
        Exercise exerciseToSave = Exercise.builder()
            .name("crunch")
            .build();

        controller.createExercise(exerciseToSave);

        assertThat(repository.findAll()).extracting("name").containsOnly("crunch");
    }

    @Test
    public void allExercisesCanBeRead() {
        Exercise crunch = Exercise.builder()
            .name("crunch")
            .build();

        Exercise squat = Exercise.builder()
            .name("squat")
            .build();

        repository.save(crunch);
        repository.save(squat);

        assertThat(controller.readAllExercises()).extracting("name").contains("crunch");
        assertThat(controller.readAllExercises()).extracting("name").contains("squat");
    }

    @Test
    public void exercisesCanBeDeleted() {
        Exercise exerciseToDelete = Exercise.builder()
            .name("crunch")
            .build();
        repository.save(exerciseToDelete);

        controller.deleteExerciseByName("crunch");

        assertThat(repository.findAll()).isNullOrEmpty();
    }
}
