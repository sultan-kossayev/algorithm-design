package graph;

import utils.Graph;

/**
 * Checks whether a graph is bipartite i.e. two colorable
 *
 * Time O(E + V), space O(V)
 */
public class BipartiteGraph {

    // indicates whether a vertex at i index has been discovered or not
    private boolean[] discovered;

    // indicates whether a graph is bipartite
    private boolean bipartite;

    // stores a color of a vertex at index i
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

