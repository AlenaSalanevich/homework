package com.cisco.training.homeworkapplication.node;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SimpleGNode implements GNode {

    @NonNull
    private String name;

    private List<GNode> children;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<GNode> getChildren() {
        return children != null ? children : new ArrayList<>();
    }
}
