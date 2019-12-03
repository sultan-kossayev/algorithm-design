package utils;

import java.util.Arrays;

public class Graph {
    private int[] vertices;
    private int[][] edges;

    public Graph(int[][] edges) {
        this.edges = edges;
        vertices = new int[edges.length];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = i;
        }
    }

    public int[] adj(int v) {
        return edges[v];
    }

    public int numberOfVertices() {
        return vertices.length;
    }

    public int[] vertices() {
        return vertices;
    }

    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        list.append("[");
        for (int[] e : edges) {
            list.append(Arrays.toString(e)).append(",");
        }

        if (list.length() > 1) {
            list.deleteCharAt(list.length() - 1);
        }

        list.append("]");

        return list.toString();
    }
}
