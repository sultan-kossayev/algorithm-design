package graph;

import utils.Graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Finds topological ordering in a directed acyclic graph
 *
 * Time O(E + V), space O(V)
 */
public class TopologicalSort {

    // topological order
    private Deque<Integer> order;

    // discovered vertices
    private boolean[] discovered;

    // processed vertices
    private boolean[] processed;

    public TopologicalSort(Graph g) {
        int V = g.numberOfVertices();

        discovered = new boolean[V];
        processed = new boolean[V];

        order = new LinkedList<>();

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
                visitVertex(g, v);
            } else if (!processed[v]) {
                // topological sort can't be found in a cyclic graph
                throw new IllegalStateException("The graph is a cyclic");
            }
        }

        processed[u] = true;

        // reverse order is a topological sort in DAG
        order.push(u);
    }

    public List<Integer> ordering() {
        return new ArrayList<>(order);
    }
}

