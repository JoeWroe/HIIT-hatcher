package com.hiithatcher.hiithatcherapi.integration;

import com.hiithatcher.hiithatcherapi.models.Exercise;
import com.hiithatcher.hiithatcherapi.repositories.ExercisesRepository;
import com.hiithatcher.hiithatcherapi.controllers.ExercisesController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@ComponentScan("com.hiithatcher.hiithatcherapi.controllers")
public class ExercisesIntegrationTest {

    @Autowired
    private ExercisesController exercisesController;

    @Test
    public void newExercisesCanBeSaved(@Autowired ExercisesRepository exercisesRepository) {
        Exercise exerciseToSave = Exercise.builder()
            .name("crunch")
            .build();

        exercisesController.createExercise(exerciseToSave);

        assertThat(exercisesRepository.findAll()).extracting("name").containsOnly("crunch");
    }

    @Test
    public void exercisesCanBeDeleted(@Autowired ExercisesRepository exercisesRepository) {
        Exercise exerciseToDelete = Exercise.builder()
            .name("crunch")
            .build();
        exercisesRepository.save(exerciseToDelete);

        exercisesController.deleteExerciseByName("crunch");

        assertThat(exercisesRepository.findAll()).isNullOrEmpty();
    }
}
