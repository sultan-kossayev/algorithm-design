package graph;

import utils.Graph;

/**
 * Checks whether a graph is bipartite i.e. two colorable
 * <p>
 * Time O(E + V), Space O(V)
 * <p>
 * Useful resources:
 * 1) 1) Sedgewick's book, page 547
 */
public class BipartiteGraph {

    private boolean bipartite;
    private boolean[] discovered;
    private boolean[] color;

    public BipartiteGraph(Graph g) {
        discovered = new boolean[g.numberOfVertices()];
        color = new boolean[g.numberOfVertices()];
        bipartite = true;

        for (int u : g.vertices()) {
            if (!discovered[u]) {
                visitVertex(g, u);
            }
        }
    }

    private void visitVertex(Graph g, int u) {
        discovered[u] = true;

        for (int v : g.adj(u)) {
            if (!discovered[v]) {
                color[v] = !color[u];
                visitVertex(g, v);
            } else if (color[v] == color[u]) {
                bipartite = false;
            }
        }
    }

    public boolean isBipartite() {
        return bipartite;
    }
}

