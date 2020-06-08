package com.hiithatcher.hiithatcherapi.controllers;

import com.hiithatcher.hiithatcherapi.models.Exercise;
import com.hiithatcher.hiithatcherapi.repositories.ExercisesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ExercisesController.class)
class ExercisesControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ExercisesRepository repository;

    Exercise crunch;
    Exercise squat;

    @BeforeEach
    void setUp() {
        crunch = Exercise.builder()
            .name("crunch")
            .build();

        squat = Exercise.builder()
            .name("squat")
            .build();
    }

    @Test
    void shouldReturnAllExercises() throws Exception {
        when(repository.findAll()).thenReturn(List.of(crunch, squat));

        mvc.perform(get("/api/exercises/")
        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].name", is("crunch")))
            .andExpect(jsonPath("$[1].name", is("squat")));
    }

    @Test
    void shouldReturnAnExerciseWithTheGivenName() throws Exception {
        when(repository.findByName("crunch")).thenReturn(crunch);

        mvc.perform(get("/api/exercises/crunch")
        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is("crunch")));
    }
}