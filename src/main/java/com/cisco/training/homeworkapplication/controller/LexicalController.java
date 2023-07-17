package com.cisco.training.homeworkapplication.controller;

import com.cisco.training.homeworkapplication.controller.annotation.ValidMediaType;
import com.cisco.training.homeworkapplication.service.LexicalService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alena_Salanevich
 */
@Validated
@RestController
@RequestMapping(LexicalController.LEXICAL_ANALYZER_BASE_PATH)
public class LexicalController {

    public static final String LEXICAL_ANALYZER_BASE_PATH = "/lexical/analyzer";
    public static final String UPLOAD_PATH = "/upload";
    private final LexicalService service;

    public LexicalController(LexicalService service) {
        this.service = service;
    }

    @ApiOperation(value = "Perform text analyze for simple text input")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PostMapping
    public Map<String, Integer> analyzeText(@ApiParam(name = "Text to analyze", required = true) @RequestParam(name = "text") String text) {
        return service.analyzeText(text);
    }

    @PostMapping(value = UPLOAD_PATH)
    public Map<String, Integer> analyzeFile(@ApiParam(name = "File to analyze", required = true) @RequestParam(name = "file") @ValidMediaType MultipartFile file) throws IOException {
        return file.isEmpty() ? new HashMap<>() : service.analyzeFile(file.getInputStream());
    }
}
