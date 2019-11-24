package graph;

import utils.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Classifies edges of a simple undirected graph using recursive dfs
 */
public class EdgeClassification {

    public List<Edge> classify(Graph g) {
        List<Edge> edges = new ArrayList<>();
        boolean[] discovered = new boolean[g.numberOfVertices()];
        boolean[] processed = new boolean[g.numberOfVertices()];

        for (int x : g.vertices()) {
            if (!discovered[x]) {
                discovered[x] = true;
                visitVertex(g, x, x, discovered, processed, edges);
            }
        }

        return edges;
    }

    private void visitVertex(Graph g, int u, int parent, boolean[] discovered, boolean[] processed, List<Edge> edges) {

        for (int v : g.adj(u)) {
            if (!discovered[v]) {
                processEdge(u, v, Edge.Type.TREE, edges);
                discovered[v] = true;
                visitVertex(g, v, u, discovered, processed, edges);
            } else if (!processed[v] && parent != v) { // to process the edge only one time
                processEdge(u, v, Edge.Type.BACKWARD, edges);
            }
        }

        processed[u] = true;
    }

    private void processEdge(int u, int v, Edge.Type type, List<Edge> edges) {
        edges.add(new Edge(u, v, type));
    }

    public static class Edge {

        public int u;
        public int v;
        public Type type;

        public Edge(int u, int v, Type t) {
            this.u = u;
            this.v = v;
            this.type = t;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }

            if (!(o instanceof Edge)) {
                return false;
            }

            Edge e = (Edge) o;

            return this.u == e.u
                    && this.v == e.v
                    && this.type == e.type;
        }

        @Override
        public String toString() {
            return "(" + this.u + ", " + this.v + ", " + this.type + ")";
        }

        public enum Type {
            TREE,
            BACKWARD;
        }
    }
}

