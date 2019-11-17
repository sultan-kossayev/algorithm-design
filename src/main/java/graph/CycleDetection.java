package graph;

import utils.Graph;

/**
 * Detects a cycle in a simple undirected graph
 * <p>
 * Time: O(V + E)
 */
public class CycleDetection {

    /**
     * The key in detecting a cycle in a simple undirected graph
     * is to find a backward edge.
     */
    public boolean hasCycle(Graph g) {
        boolean[] discovered = new boolean[g.numberOfVertices()];

        for (int x : g.vertices()) {
            if (!discovered[x]) {
                discovered[x] = true;

                if (visitVertex(g, x, x, discovered)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean visitVertex(Graph g, int v, int parent, boolean[] discovered) {
        for (int u : g.adj(v)) {
            if (!discovered[u]) {
                discovered[u] = true;
                if (visitVertex(g, u, v, discovered)) {
                    return true;
                }
            } else if (u != parent) {

                // if v's neighbor has been discovered and it is not v's parent
                // then (v, u) is a backward edge which leads to a conclusion that the current graph has cycles
                return true;
            }
        }

        return false;
    }
}

