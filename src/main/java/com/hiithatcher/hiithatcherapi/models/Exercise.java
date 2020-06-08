package com.hiithatcher.hiithatcherapi.models;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Builder
@Data
public class Exercise {

    @Id
    private ObjectId _id;

    private final String name;
}
