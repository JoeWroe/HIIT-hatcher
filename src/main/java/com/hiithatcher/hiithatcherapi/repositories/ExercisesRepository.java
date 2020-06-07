package com.hiithatcher.hiithatcherapi.repositories;

import com.hiithatcher.hiithatcherapi.models.Exercise;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExercisesRepository extends MongoRepository<Exercise, String> {
    Exercise findByName(String name);
}
