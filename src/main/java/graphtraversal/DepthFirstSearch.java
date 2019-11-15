package graphtraversal;

import utils.Graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Two implementations of depth-first search on a graph:
 * 1) recursive. Time O(V+E), Space O(V)
 * 2) iterative. Time O(V+E), Space O(V)
 *
 * Both implementations return a list of vertices in order that they have been discovered
 */
public class DepthFirstSearch {

    /**
     * Recursive approach
     */
    public List<Integer> recursiveTraversal(Graph g) {
        boolean[] discovered = new boolean[g.numberOfVertices()];

        List<Integer> res = new ArrayList<>(); // keeps vertices in discovered

        for (int v : g.vertices()) {
            if (!discovered[v]) {
                // discover the vertex v
                discovered[v] = true;
                res.add(v);
                // go and process the vertex v
                recursive(g, v, discovered, res);
            }
        }

        return res;
    }

    private void recursive(Graph g, int v, boolean[] discovered, List<Integer> res) {
        // process the vertex v early
        res.add(v);

        for (int u : g.adj(v)) {
            if (!discovered[u]) {
                // process (v,u) edge

                // discover the vertex v
                discovered[u] = true;

                // go and process the vertex u
                recursive(g, u, discovered, res);
            }
            // else if the vertex u is not processed OR the graph is directed then also process (v, u) edge
        }

        // process the vertex v late
        // mark the vertex v as processed
    }

    /**
     * Iterative approach using stack.
     *
     * Note, there is an another way to implement iterative algorithm using stack
     * where we allow the stack to contain duplicate values. However, with that approach it is not easy
     * to process the vertex after all its adjacent vertices have been processed.
     */
    public List<Integer> iterativeTraversal(Graph g) {
        boolean[] discovered = new boolean[g.numberOfVertices()];

        List<Integer> res = new ArrayList<>(); // this is only needed for test cases

        Deque<Integer> stack = new LinkedList<>();

        for (int x : g.vertices()) {
            if (!discovered[x]) {
                // discover the vertex x
                discovered[x] = true;

                // go and process the vertex x
                stack.push(x);

                while (!stack.isEmpty()) {
                    int v = stack.peek();

                    if (!discovered[v]) {
                        // process the vertex v early
                        res.add(v);
                    }

                    boolean allAdjacentProcessed = true;
                    for (int u : g.adj(v)) {
                        if (!discovered[u]) {
                            // process the edge (v, u)

                            // discover the vertex v
                            discovered[v] = true;

                            // go and process  the vertex u
                            stack.push(u);

                            allAdjacentProcessed = false;
                            break;
                        }
                        // else if the vertex u is not processed OR the graph is directed then also process the edge (v, u)
                    }

                    if (allAdjacentProcessed) {
                        // process the vertex v late

                        // mark the vertex as processed
                        stack.pop();
                    }
                }
            }
        }

        return res;
    }
}
