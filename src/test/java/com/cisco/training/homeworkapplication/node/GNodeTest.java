package com.cisco.training.homeworkapplication.node;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GNodeTest {

    public static List<GNode> walkGraph(GNode gNode) {
        Set<GNode> result = new HashSet<>(gNode.getChildren());
        result.addAll(gNode.getChildren().stream().flatMap(c -> walkGraph(c).stream()).collect(Collectors.toSet()));
        return new ArrayList<>(result);
    }

    public static List<List<GNode>> allPaths(GNode gNode) {
        List<GNode> initialEdge = new ArrayList<>();
        initialEdge.add(gNode);
        List<List<GNode>> edges = new ArrayList<>();
        edges.add(initialEdge);
        return paths(gNode, edges);
    }

    public static List<List<GNode>> paths(GNode gNode, List<List<GNode>> edges) {
        if (gNode.getChildren().size() != 0) {
            return gNode.getChildren().stream().map(c -> {
                List<List<GNode>> childEdges = new ArrayList<>(edges);
                childEdges.forEach(ce -> ce.add(c));
                return paths(c, childEdges);
            }).flatMap(Collection::stream).collect(Collectors.toList());
        }
        return edges;
    }

    private static Stream<Arguments> nodeDataGraphArguments() {
        SimpleGNode d = new SimpleGNode("D");
        SimpleGNode c = new SimpleGNode("C", Collections.singletonList(d));
        SimpleGNode e = new SimpleGNode("E", Collections.singletonList(d));
        SimpleGNode f = new SimpleGNode("F");
        SimpleGNode g = new SimpleGNode("G", Arrays.asList(d, e, c, f));
        SimpleGNode i = new SimpleGNode("I", Arrays.asList(d, g, c, f));
        SimpleGNode b = new SimpleGNode("B", Arrays.asList(d, f, c, e, i));
        SimpleGNode l = new SimpleGNode("L", Arrays.asList(d, i));

        return Stream.of(Arguments.of(new SimpleGNode("A", Arrays.asList(d, c, e, b, l))),
                Arguments.of(new SimpleGNode("A", Arrays.asList(d, b, l))),
                Arguments.of(new SimpleGNode("A", Arrays.asList(d, c, e, b, l, i, f, g))));
    }

    private static Stream<Arguments> nodeDataPathArguments() {
        SimpleGNode e = new SimpleGNode("E");
        SimpleGNode f = new SimpleGNode("F");
        SimpleGNode b = new SimpleGNode("B", Arrays.asList(e, f));
        SimpleGNode g = new SimpleGNode("G");
        SimpleGNode i = new SimpleGNode("I");
        SimpleGNode h = new SimpleGNode("H");
        SimpleGNode c = new SimpleGNode("C", Arrays.asList(g, h, i));
        SimpleGNode j = new SimpleGNode("J");
        SimpleGNode d = new SimpleGNode("D", Collections.singletonList(j));

        return Stream.of(Arguments.of(new SimpleGNode("A", Arrays.asList(b, c, d)), 6),
                Arguments.of(new SimpleGNode("A", Arrays.asList(b, d, i)), 4),
                Arguments.of(new SimpleGNode("A", Arrays.asList(c, b, d, i)), 7));
    }

    @ParameterizedTest
    @MethodSource("nodeDataGraphArguments")
    void walkGraphNode(GNode gNode) {
        List<GNode> result = walkGraph(gNode);
        assertNotNull(result);
        assertEquals(8, result.size());
    }

    @ParameterizedTest
    @MethodSource("nodeDataPathArguments")
    void pathsNode(GNode gNode, int size) {
        List<List<GNode>> result = allPaths(gNode);
        assertNotNull(result);
        assertEquals(size, result.size());
    }
}