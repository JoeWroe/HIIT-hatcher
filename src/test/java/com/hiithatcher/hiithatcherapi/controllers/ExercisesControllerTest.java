package com.hiithatcher.hiithatcherapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiithatcher.hiithatcherapi.models.Exercise;
import com.hiithatcher.hiithatcherapi.services.ExercisesService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ExercisesController.class)
class ExercisesControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ExercisesService service;

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
    void shouldCreateAnExercise() throws Exception {
        when(service.createSingleExercise(crunch)).thenReturn(crunch);

        mvc.perform(post("/api/exercises/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(crunch)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is("crunch")));

        verify(service, times(1)).createSingleExercise(crunch);
    }

    @Test
    void shouldReturnAllExercises() throws Exception {
        when(service.readAllExercises()).thenReturn(List.of(crunch, squat));

        mvc.perform(get("/api/exercises/")
        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].name", is("crunch")))
            .andExpect(jsonPath("$[1].name", is("squat")));

        verify(service, times(1)).readAllExercises();
    }

    @Test
    void shouldReturnAnExerciseWithTheGivenName() throws Exception {
        when(service.readSingleExercise("crunch")).thenReturn(crunch);

        mvc.perform(get("/api/exercises/crunch")
        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is("crunch")));

        verify(service, times(1)).readSingleExercise("crunch");
    }

    @Test
    void shouldDeleteAnExercise() throws Exception {
        doNothing().when(service).deleteSingleExercise("crunch");

        mvc.perform(delete("/api/exercises/crunch")
        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        verify(service, times(1)).deleteSingleExercise("crunch");
    }
}