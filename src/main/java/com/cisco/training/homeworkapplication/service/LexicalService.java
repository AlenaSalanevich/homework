package com.cisco.training.homeworkapplication.service;

import java.io.InputStream;
import java.util.Map;

/**
 * @author Alena_Salanevich
 */
public interface LexicalService {


    Map<String, Integer> analyzeText(String text);

    Map<String, Integer> analyzeFile(InputStream file);

}
