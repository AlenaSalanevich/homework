package com.cisco.training.homeworkapplication.service.impl;

import com.cisco.training.homeworkapplication.service.LexicalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LexicalTagServiceImplTest {

    private LexicalService underTest;

    @BeforeEach
    void setUp() {
        underTest = new LexicalServiceImpl();
    }

    @Test
    void analyzeText() {
        String text = "Mama: maMa, ma. Ta la - la la";
        Map<String, Integer> expectedResult = Map.of("mama", 2, "ma", 1, "ta", 1, "la", 3);
        Map<String, Integer> result = underTest.analyzeText(text);
        assertNotNull(result);
        assertEquals(4, result.keySet().size());
        assertEquals(3, result.get("la"));
        assertThat(expectedResult).isEqualTo(result);
    }

    @Test
    void analyzeTextEmpty() {
        String text = "  ";
        Map<String, Integer> result = underTest.analyzeText(text);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test()
    void analyzeFile() throws IOException {
        Path file = Paths.get("src/test/resources/input_text.txt");
        Map<String, Integer> result = underTest.analyzeFile(new FileInputStream(file.toFile()));
        assertNotNull(result);
        assertEquals(23, result.keySet().size());
    }

    @Test()
    void analyzeEmptyFile() throws IOException {
        Path file = Paths.get("src/test/resources/input_empty.txt");
        Map<String, Integer> result = underTest.analyzeFile(new FileInputStream(file.toFile()));
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}