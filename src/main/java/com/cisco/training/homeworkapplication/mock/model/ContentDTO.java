package com.cisco.training.homeworkapplication.mock.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
public class ContentDTO {

    private String content;

    private Collection<String> tags;
}
