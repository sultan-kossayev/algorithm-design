package utils;

public class Graph {
    private int[][] edges;

    public Graph(int[][] edges) {
        this.edges = edges;
    }

    public int[] adj(int v) {
        return edges[v];
    }

    public int numberOfVertices() {
        return edges.length;
    }
}
