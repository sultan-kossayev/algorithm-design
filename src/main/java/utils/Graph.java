package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public Graph(List<List<Integer>> edges) {
        this.vertices = new int[edges.size()];
        this.edges = new int[edges.size()][];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = i;
            this.edges[i] = edges.get(i).stream().mapToInt(e -> e).toArray();
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

    public Graph reverseCopy() {
        List<List<Integer>> copy = new ArrayList<>();
        for (int v : vertices) {
            copy.add(new ArrayList<>());
        }

        for (int v : vertices) {
            for (int e : edges[v]) {
                copy.get(e).add(v);
            }
        }

        return new Graph(copy);
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
