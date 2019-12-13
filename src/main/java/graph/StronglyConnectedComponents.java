package graph;

import utils.Graph;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Implementation of Kosaraju's algorithm to find
 * strongly connected components in a directed graph
 * <p>
 * Time O(E + V), space O(V)
 */
public class StronglyConnectedComponents {

    // strongly connected components. Read as "a vertex at index i belongs to scc[i] component"
    private int[] scc;

    // the number of strongly connected components
    private int components;

    // already visited vertices
    private boolean[] discovered;

    // vertices in the processed order
    private Deque<Integer> processed;

    public StronglyConnectedComponents(Graph g) {
        scc = new int[g.numberOfVertices()];
        discovered = new boolean[g.numberOfVertices()];
        processed = new LinkedList<>();
        components = 0;

        // do dfs on the initial graph. as soon as a vertex gets processed add it to a stack
        for (int u : g.vertices()) {
            if (!discovered[u]) {
                processVertex(g, u);
            }
        }

        // reverse g
        Graph gr = g.reverseCopy();

        // reset
        discovered = new boolean[gr.numberOfVertices()];

        // do dfs on the reversed graph using processed vertices from the above dfs.
        for (int u : processed) {
            if (!discovered[u]) {
                assignSSComponent(gr, u, components);
                components++;
            }
        }
    }

    private void processVertex(Graph g, int u) {
        discovered[u] = true;

        for (int v : g.adj(u)) {
            if (!discovered[v]) {
                processVertex(g, v);
            }
        }

        // processed vertices are added to the stack
        processed.push(u);
    }

    private void assignSSComponent(Graph g, int u, int componentId) {
        discovered[u] = true;
        // assigning component id to u vertex
        scc[u] = componentId;

        for (int v : g.adj(u)) {
            if (!discovered[v]) {
                assignSSComponent(g, v, componentId);
            }
        }
    }

    public boolean stronglyConnected(int u, int v) {
        return scc[u] == scc[v];
    }

    public int count() {
        return components;
    }
}
