package com.cisco.training.homeworkapplication.controller;

import com.cisco.training.homeworkapplication.model.Content;
import com.cisco.training.homeworkapplication.model.Tag;
import com.cisco.training.homeworkapplication.service.ContentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class ContentControllerTest {

    @Mock
    private ContentService service;

    private MockMvc mvc;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(new MockedContentController(service), new ContentController(service)).build();
        reset(service);
    }

    @Test
    void getMockedContents() throws Exception {
        Content content = new Content();
        content.setContentId(1);
        content.setContent("someTestContent");
        Tag tag = new Tag();
        tag.setName("someTag");
        content.setTags(Collections.singleton(tag));
        when(service.getByTag(anyString())).thenReturn(Collections.singletonList(content));
        mvc.perform(get("/mocked/contents").param("tag", "tag"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content.getContent()));
    }

    @Test
    void getContents() throws Exception {
        Content content = new Content();
        content.setContentId(1);
        content.setContent("someTestContent");
        Tag tag = new Tag();
        tag.setName("someTag");
        content.setTags(Collections.singleton(tag));
        when(service.getByTag(anyString())).thenReturn(Collections.singletonList(content));
        mvc.perform(get("/contents").param("tag", "tag"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content.getContent()));
    }
}