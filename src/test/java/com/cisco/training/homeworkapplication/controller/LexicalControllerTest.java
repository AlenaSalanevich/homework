package com.cisco.training.homeworkapplication.controller;

import com.cisco.training.homeworkapplication.service.LexicalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static com.cisco.training.homeworkapplication.controller.LexicalController.LEXICAL_ANALYZER_BASE_PATH;
import static com.cisco.training.homeworkapplication.controller.LexicalController.UPLOAD_PATH;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class LexicalControllerTest {

    @Mock
    private LexicalService service;
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(new LexicalController(service)).build();
    }


    @Test
    void analyzeText() throws Exception {
        when(service.analyzeText(anyString())).thenReturn(Map.of("text", 2));
        mvc.perform(post(LEXICAL_ANALYZER_BASE_PATH)
                        .param("text", "Some text: to verify functionality for some text!"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("2"));
    }

    @Test
    void analyzeFile() throws Exception {
        when(service.analyzeText(anyString())).thenReturn(Map.of("text", 2));
        MockMultipartFile file = new MockMultipartFile("file", "input.txt", MediaType.TEXT_PLAIN_VALUE, "Some text: to verify functionality for some text!".getBytes(StandardCharsets.UTF_8));
        file.isEmpty();
        mvc.perform(multipart(LEXICAL_ANALYZER_BASE_PATH + UPLOAD_PATH)
                        .file(file))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("2"));
    }
}