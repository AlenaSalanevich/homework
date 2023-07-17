package com.cisco.training.homeworkapplication.mock;

import com.cisco.training.homeworkapplication.mock.model.TagDTO;
import com.cisco.training.homeworkapplication.mock.model.Tagged;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class MockTagRepository {

    private final ObjectMapper mapper;

    public MockTagRepository(ObjectMapper mapper) {
        this.mapper = new ObjectMapper();
    }

    public Collection<? extends Tagged> getTags() {
        try (InputStream in = new ClassPathResource("/tags.json").getInputStream()) {
            return mapper.readValue(in, new TypeReference<Collection<TagDTO>>() {
            });
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
