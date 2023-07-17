package com.cisco.training.homeworkapplication.mock.service;

import com.cisco.training.homeworkapplication.mock.MockContentRepository;
import com.cisco.training.homeworkapplication.mock.model.ContentDTO;
import com.cisco.training.homeworkapplication.model.Content;
import com.cisco.training.homeworkapplication.service.ContentService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service("mockedContentService")
public class MockContentService implements ContentService {
    private final MockContentRepository contentRepository;
    private final MockTagService tagService;

    public MockContentService(MockContentRepository contentRepository, MockTagService tagService) {
        this.contentRepository = contentRepository;
        this.tagService = tagService;
    }

    private static boolean isTagged(Collection<String> tags, ContentDTO c) {
        return c.getTags().stream().anyMatch(tags::contains);
    }

    @Override
    public Collection<Content> getByTag(String tag) {
        Collection<String> tags = tagService.getSubTags(tag);
        Collection<ContentDTO> contents = contentRepository.getContents();
        return contents.stream().filter(c -> c.getTags() != null || !c.getTags().isEmpty()).
                filter(c -> isTagged(tags, c)).map(this::toModel).collect(Collectors.toSet());
    }

    private Content toModel(ContentDTO c) {
        Content content = new Content();
        content.setContent(c.getContent());
        content.setContentId(ThreadLocalRandom.current().nextInt(1, 100));
        return content;
    }
}
