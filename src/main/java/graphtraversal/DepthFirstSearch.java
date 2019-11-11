package graphtraversal;

import utils.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Depth-first search of graph in 2 ways:
 * 1) recursive. Time O(V+E), Space O(V)
 * 2) iterative. Time O(V+E), Space O(V)
 */
public class DepthFirstSearch {

    public List<Integer> recursiveTraversal(Graph g, int start) {
        boolean[] visited = new boolean[g.numberOfVertices()];

        List<Integer> res = new ArrayList<>(); // this is needed only for test cases

        recursive(g, start, visited, res);

        return res;
    }

    private void recursive(Graph g, int v, boolean[] visited, List<Integer> res) {
        visited[v] = true; // this is the key. we mark vertices that we visited already, so that we don't go in cycle
        res.add(v);

        for (int u : g.adj(v)) {
            if (!visited[u]) {
                recursive(g, u, visited, res);
            }
        }
    }
}
