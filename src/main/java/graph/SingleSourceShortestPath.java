package graph;

import utils.Graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Single source shortest path problem on an unweighted graph
 * The program finds a path to every vertex in a graph using bfs.
 * A path represents the bfs tree.
 * <p>
 * Useful resources:
 * 1) Sedgewick's book, page 538
 * <p>
 * Time O(E + V), space O(V)
 */
public class SingleSourceShortestPath {

    private boolean[] discovered;

    // vertex at index i is discovered by discoveredBy[i] vertex
    private int[] discoveredBy;

    public SingleSourceShortestPath(Graph g) {
        this.discovered = new boolean[g.numberOfVertices()];
        this.discoveredBy = new int[g.numberOfVertices()];

        for (int u : g.vertices()) {
            if (!discovered[u]) {
                bfs(g, u);
            }
        }
    }

    private void bfs(Graph g, int u) {
        discovered[u] = true;
        discoveredBy[u] = u;

        Queue<Integer> q = new LinkedList<>();
        q.offer(u);
        while (!q.isEmpty()) {
            int x = q.poll();

            for (int y : g.adj(x)) {
                if (!discovered[y]) {
                    discovered[y] = true;
                    discoveredBy[y] = x;
                    q.offer(y);
                }
            }
        }
    }

    public List<Integer> pathTo(int u) {
        Deque<Integer> path = new LinkedList<>();
        int x;
        for (x = u; x != discoveredBy[x]; x = discoveredBy[x]) {
            path.push(x);
        }

        path.push(x);

        return (List) path;
    }
}
