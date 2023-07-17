package com.cisco.training.homeworkapplication.mock;

import com.cisco.training.homeworkapplication.mock.model.ContentDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class MockContentRepository {
    private final ObjectMapper mapper;

    public MockContentRepository(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Collection<ContentDTO> getContents() {
        try (InputStream in = new ClassPathResource("/contents.json").getInputStream()) {
            return mapper.readValue(in, new TypeReference<>() {
            });
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
