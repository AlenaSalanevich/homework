package com.cisco.training.homeworkapplication.mock.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
public class TagDTO implements Tagged {

    private String name;
    private Collection<TagDTO> subTags;

}
