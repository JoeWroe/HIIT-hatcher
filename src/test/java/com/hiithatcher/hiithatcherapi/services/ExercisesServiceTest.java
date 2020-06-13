package com.hiithatcher.hiithatcherapi.services;

import com.hiithatcher.hiithatcherapi.models.Exercise;
import com.hiithatcher.hiithatcherapi.repositories.ExercisesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ExercisesServiceTest {

    @MockBean
    private ExercisesRepository repository;

    private ExercisesService service;
    private Exercise crunch;
    private Exercise squat;

    @BeforeEach
    void setUp() {
        service = new ExercisesService(repository);

        crunch = Exercise.builder()
            .name("crunch")
            .build();

        squat = Exercise.builder()
            .name("squat")
            .build();
    }

    @Test
    void shouldReadAllExercisesFromTheRepository() {
        when(repository.findAll()).thenReturn(List.of(crunch, squat));

        assertThat(service.readAllExercises()).extracting("name").contains("crunch", "squat");
    }
}