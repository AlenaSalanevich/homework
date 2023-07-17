package com.cisco.training.homeworkapplication.service.impl;

import com.cisco.training.homeworkapplication.model.Content;
import com.cisco.training.homeworkapplication.repository.ContentRepository;
import com.cisco.training.homeworkapplication.service.ContentService;

import java.util.Collection;
import java.util.stream.StreamSupport;

public class ContentServiceImpl implements ContentService {

    private final ContentRepository repository;

    public ContentServiceImpl(ContentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Content> getByTag(String tag) {
        return tag.isBlank() ? StreamSupport.stream(repository.findAll().spliterator(), false).toList() : repository.getAllByTag(tag);
    }
}
