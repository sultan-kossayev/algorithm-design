package graph;

import utils.Graph;

/**
 * Detects a cycle in a graph
 *
 * Time: O(V + E)
 */
public class CycleDetection {

    // indicates that a vertex at index i was discovered and all its adjacent vertices were visited
    private boolean[] processed;

    // indicates that a vertex at index i was discovered
    private boolean[] discovered;

    // indicates whether a graph is cyclic
    private boolean cyclic;

    public CycleDetection(Graph g) {

        processed = new boolean[g.numberOfVertices()];
        discovered = new boolean[g.numberOfVertices()];
        cyclic = false;

        for (int u : g.vertices()) {
            if (!discovered[u]) {
                visitVertex(g, u, u);
            }
        }
    }

    private void visitVertex(Graph g, int u, int parent) {
        discovered[u] = true;

        for (int v : g.adj(u)) {
            if (v == parent) {
                // this check is only relevant for undirected graphs. We don't want to process the same edge twice
                continue;
            }

            if (!discovered[v]) {
                visitVertex(g, v, u);
            } else if (!processed[v]) {
                //The key in detecting a cycle in a simple undirected graph is to find a backward edge.
                cyclic = true;
            }
        }

        processed[u] = true;
    }

    public boolean hasCycle() {
        return this.cyclic;
    }
}

