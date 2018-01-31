package org.lbulic.helper;

import java.util.Map;

public class Graph {

    private final String name;
    private final Map<Integer, GraphVertex> vertices;

    public Graph(String name, Map<Integer, GraphVertex> vertices) {
        this.name = name;
        this.vertices = vertices;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, GraphVertex> getVertices() {
        return vertices;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "name='" + name + '\'' +
                ", vertices=" + vertices +
                '}';
    }
}
