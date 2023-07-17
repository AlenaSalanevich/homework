package com.cisco.training.homeworkapplication.service.impl;

import com.cisco.training.homeworkapplication.exception.InvalidFileException;
import com.cisco.training.homeworkapplication.service.LexicalService;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;

/**
 * @author Alena_Salanevich
 */
public class LexicalServiceImpl implements LexicalService {
    public static final String DELIMITERS = "[\\p{Punct}\\s]+";

    @Override
    public Map<String, Integer> analyzeText(String text) {
        return text.isBlank() ? new HashMap<>() : Stream.of(text.split(DELIMITERS))
                .filter(w -> !w.isEmpty() || !w.isBlank())
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toMap(k -> k, (v) -> 1, (e, r) -> e + 1));
    }

    @Override
    public Map<String, Integer> analyzeFile(InputStream file) {
        try {
            return this.analyzeText(IOUtils.toString(file, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new InvalidFileException(format("Unable to read data from file: %s", e.getMessage()), e);
        }
    }
}
