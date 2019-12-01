package graph;

import utils.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Finds connected components in a graph using
 * DFS, BFS
 */
public class ConnectedComponents {

    /**
     * Time O(E + V), space O(V)
     */
    public List<List<Integer>> findUsingDFS(Graph g) {

        boolean[] discovered = new boolean[g.numberOfVertices()];
        List<List<Integer>> components = new ArrayList<>();
        for (int u : g.vertices()) {
            if (!discovered[u]) {
                discovered[u] = true;
                List<Integer> component = new ArrayList<>();
                component.add(u);
                visitVertex(g, u, discovered, component);
                components.add(component);
            }
        }

        return components;
    }

    private void visitVertex(Graph g, int u, boolean[] discovered, List<Integer> component) {

        for (int v : g.adj(u)) {
            if (!discovered[v]) {
                discovered[v] = true;
                component.add(v);
                visitVertex(g, v, discovered, component);
            }
        }
    }

    /**
     * Time O(E + V), Space O(V)
     */
    public List<List<Integer>> findUsingBFS(Graph g) {
        boolean[] discovered = new boolean[g.numberOfVertices()];
        List<List<Integer>> components = new ArrayList<>();

        for (int u : g.vertices()) {
            if (!discovered[u]) {
                discovered[u] = true;
                List<Integer> component = new ArrayList<>();
                component.add(u);

                Queue<Integer> q = new LinkedList<>();
                q.offer(u);

                while (!q.isEmpty()) {
                    int x = q.poll();

                    for (int y : g.adj(x)) {
                        if (!discovered[y]) {
                            discovered[y] = true;
                            component.add(y);
                            q.offer(y);
                        }
                    }
                }

                components.add(component);
            }
        }

        return components;
    }

}

