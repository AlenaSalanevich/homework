package com.cisco.training.homeworkapplication.controller;

import com.cisco.training.homeworkapplication.model.Content;
import com.cisco.training.homeworkapplication.service.ContentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/mocked/contents")
public class MockedContentController {

    private final ContentService service;

    public MockedContentController(@Qualifier("mockedContentService") ContentService service) {
        this.service = service;
    }

    @ApiOperation(value = "Retrieve content relevant to tag")
    @GetMapping
    Collection<Content> getContents(@ApiParam(required = true, name = "tag") @RequestParam String tag) {
        return service.getByTag(tag);
    }
}
