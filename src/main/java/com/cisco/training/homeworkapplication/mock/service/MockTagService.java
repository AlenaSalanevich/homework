package com.cisco.training.homeworkapplication.mock.service;

import com.cisco.training.homeworkapplication.mock.MockTagRepository;
import com.cisco.training.homeworkapplication.mock.model.Tagged;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service("mockTagService")
public class MockTagService {

    private final MockTagRepository repository;

    public MockTagService(MockTagRepository repository) {
        this.repository = repository;
    }

    public Collection<String> getSubTags(String tag) {
        Set<String> children = new HashSet<>();
        for (Tagged child : repository.getTags()) {
            getSubTags(child, tag, children);
        }
        return children;
    }

    private void getSubTags(Tagged parent, String tag, Set<String> result) {
        boolean matched = parent.getName().equalsIgnoreCase(tag);
        if (matched) {
            result.add(parent.getName());
        }
        if (parent.getSubTags() != null && parent.getSubTags().size() != 0) {
            for (Tagged child : parent.getSubTags()) {
                getSubTags(child, matched ? child.getName() : tag, result);
            }
        }
    }
}

