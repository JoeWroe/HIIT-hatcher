package com.hiithatcher.hiithatcherapi.controllers;

import com.hiithatcher.hiithatcherapi.models.Exercise;
import com.hiithatcher.hiithatcherapi.repositories.ExercisesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

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

    @Test
    void shouldReturnAnExerciseWithTheGivenName() throws Exception {
        Exercise crunch = Exercise.builder()
            .name("crunch")
            .build();

        when(repository.findByName("crunch")).thenReturn(crunch);

        mvc.perform(get("/api/exercises/crunch")
        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is("crunch")));
    }
}