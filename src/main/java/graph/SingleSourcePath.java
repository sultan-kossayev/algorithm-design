package graph;

import utils.Graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


/**
 * Single source path problem.
 * The program finds a path to every vertex in a graph using dfs.
 * A path represents the dfs tree.
 * <p>
 * Useful resources:
 * 1) Sedgewick's book, page 535
 * <p>
 * Time O(E + V), space O(V)
 */
public class SingleSourcePath {

    private boolean[] discovered;

    // vertex at index i is discovered by discoveredBy[i] vertex
    private int[] discoveredBy;

    public SingleSourcePath(Graph g) {
        this.discovered = new boolean[g.numberOfVertices()];
        this.discoveredBy = new int[g.numberOfVertices()];

        for (int u : g.vertices()) {
            if (!discovered[u]) {
                dfs(g, u, u);
            }
        }
    }

    private void dfs(Graph g, int u, int parent) {
        discovered[u] = true;
        discoveredBy[u] = parent;

        for (int v : g.adj(u)) {
            if (!discovered[v]) {
                dfs(g, v, u);
            }
        }
    }

    public List pathTo(int u) {
        Deque<Integer> path = new LinkedList<>();
        int x;
        for (x = u; x != discoveredBy[x]; x = discoveredBy[x]) {
            path.push(x);
        }

        path.push(x);

        return (List) path;
    }
}

