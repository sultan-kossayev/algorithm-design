package graph;

import utils.Graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Two implementations of depth-first search on a graph:
 * 1) recursive. Time O(V+E), Space O(V)
 * 2) iterative. Time O(V+E), Space O(V)
 */
public class DepthFirstSearch {

    /**
     * Couple of definitions that would help to understand below algorithms:
     * - Visiting a vertex means processing it and discovering all its adjacent (undiscovered) vertices
     * - A vertex is considered discovered when it has been found the first time.
     * - A vertex is considered processed when it and all its adjacent vertices have been visited.
     */


    /**
     * Recursive approach
     *
     * Returns two list of vertices in order in which they were discovered and processed
     */
    public List[] recursive(Graph g) {
        boolean[] discovered = new boolean[g.numberOfVertices()];

        List<Integer> discoveredList = new ArrayList<>();
        List<Integer> processedList = new ArrayList<>();

        for (int v : g.vertices()) {
            if (!discovered[v]) {

                // discover the vertex v
                discovered[v] = true;
                discoveredList.add(v);

                // visit the vertex v
                visitVertex(g, v, discovered, discoveredList, processedList);
            }
        }

        return new List[]{discoveredList, processedList};
    }

    private void visitVertex(Graph g, int v, boolean[] seen, List<Integer> discovered, List<Integer> processed) {
        // process the vertex v early

        for (int u : g.adj(v)) {
            if (!seen[u]) {
                // process (v,u) edge

                // discover the vertex u
                seen[u] = true;
                discovered.add(u);

                // visit the vertex u
                visitVertex(g, u, seen, discovered, processed);
            }
            // else if the vertex u is not processed OR the graph is directed then also process (v, u) edge
        }

        // process the vertex v late
        processed.add(v);
    }

    /**
     * First iterative approach. It yields the same results as the recursive approach above
     *
     * Returns two list of vertices in order in which they were discovered and processed.
     */
    public List[] iterative(Graph g) {
        boolean[] discovered = new boolean[g.numberOfVertices()];

        List<Integer> discoveredList = new ArrayList<>();
        List<Integer> processedList = new ArrayList<>();

        for (int x : g.vertices()) {
            if (!discovered[x]) {

                Deque<Integer> stack = new LinkedList<>();
                // visit the vertex v
                stack.push(x);

                // discover the vertex x
                discovered[x] = true;
                discoveredList.add(x);

                while (!stack.isEmpty()) {
                    int v = stack.peek();

                    // are all adjacent vertices visited?
                    boolean allVisited = true;
                    for (int u : g.adj(v)) {
                        if (!discovered[u]) {
                            // process the edge (v, u)

                            // discover the vertex u
                            discovered[u] = true;
                            discoveredList.add(u);

                            // visit the vertex u
                            stack.push(u);

                            allVisited = false;
                            break;
                        }
                        // else if the vertex u is not processed OR the graph is directed then also process the edge (v, u)
                    }

                    if (allVisited) {

                        // process the vertex v late
                        v = stack.pop();
                        processedList.add(v);
                    }
                }
            }
        }

        return new List[]{discoveredList, processedList};
    }


    /**
     * Second iterative approach.
     * This approach is not suitable for tracking of when a vertex is considered processed.
     * <p>
     * Also, it doesn't produce the same discovered ordering as the above recursive and iterative approaches.
     * <p>
     * Returns only a list of vertices in order in which they were discovered
     */
    public List[] iterative2(Graph g) {
        boolean[] discovered = new boolean[g.numberOfVertices()];
        List<Integer> discoveredList = new ArrayList<>();

        for (int x : g.vertices()) {
            if (!discovered[x]) {
                // discover the vertex x
                discovered[x] = true;
                discoveredList.add(x);

                Deque<Integer> stack = new LinkedList<>();
                stack.push(x);

                while (!stack.isEmpty()) {

                    // visit the vertex x
                    int v = stack.pop();

                    for (int u : g.adj(v)) {
                        if (!discovered[u]) {

                            stack.push(u);

                            // discover the vertex u
                            discovered[u] = true;
                            discoveredList.add(u);
                        }
                    }
                }
            }
        }

        return new List[]{discoveredList};
    }
}
