package utils;

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
}
