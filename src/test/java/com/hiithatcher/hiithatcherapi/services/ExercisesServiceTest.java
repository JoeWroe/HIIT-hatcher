package com.hiithatcher.hiithatcherapi.services;

import com.hiithatcher.hiithatcherapi.models.Exercise;
import com.hiithatcher.hiithatcherapi.repositories.ExercisesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
    void shouldCreateAnExerciseInTheRepository() {
        when(repository.save(crunch)).thenReturn(crunch);

        assertThat(service.createSingleExercise(crunch)).isEqualTo(crunch);
        verify(repository, times(1)).save(crunch);
    }

    @Nested
    class WhenReadingFromTheRepository {

        @Test
        void shouldRetrieveAllExercises() {
            when(repository.findAll()).thenReturn(List.of(crunch, squat));

            assertThat(service.readAllExercises()).extracting("name").contains("crunch", "squat");
            verify(repository, times(1)).findAll();
        }

        @Test
        void shouldRetrieveASingleExercise() {
            when(repository.findByName("squat")).thenReturn(squat);

            assertThat(service.readSingleExercise("squat")).isEqualTo(squat);
            verify(repository, times(1)).findByName("squat");
        }
    }

    @Test
    void shouldDeleteAnExerciseFromTheRepository() {
        when(repository.findByName("crunch")).thenReturn(crunch);
        doNothing().when(repository).delete(crunch);

        service.deleteSingleExercise("crunch");
        verify(repository, times(1)).delete(crunch);
    }
}