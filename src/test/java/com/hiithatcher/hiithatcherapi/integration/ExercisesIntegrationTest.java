package com.hiithatcher.hiithatcherapi.integration;

import com.hiithatcher.hiithatcherapi.models.Exercise;
import com.hiithatcher.hiithatcherapi.repositories.ExercisesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class ExercisesIntegrationTest {

    @Test
    public void newExercisesCanBeSaved(@Autowired ExercisesRepository exercisesRepository) {
        Exercise exerciseToSave = Exercise.builder()
            .name("crunch")
            .build();

        exercisesRepository.save(exerciseToSave);

        assertThat(exercisesRepository.findAll()).extracting("name").containsOnly("crunch");
    }
}
