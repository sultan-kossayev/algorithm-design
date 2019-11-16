package graph;

import utils.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Implementation of breadth-first search on a graph
 * <p>
 * Time: O(V + E), Space: O(V)
 */
public class BreadthFirstSearch {

    /**
     * Returns a list of vertices in order in which they were discovered
     */
    public List<Integer> search(Graph g) {
        List<Integer> discoveredList = new ArrayList<>();

        boolean[] discovered = new boolean[g.numberOfVertices()];

        for (int x : g.vertices()) {
            if (discovered[x]) {
                continue;
            }

            Queue<Integer> q = new LinkedList<>();
            q.offer(x);

            // discover the vertex x
            discovered[x] = true;
            discoveredList.add(x);

            while (!q.isEmpty()) {
                // visit the vertex v
                int v = q.poll();

                for (int u : g.adj(v)) {
                    if (!discovered[u]) {
                        // process the edge (v, u)

                        // discover the vertex u
                        discovered[u] = true;
                        discoveredList.add(u);

                        // visit the vertex u
                        q.offer(u);
                    }
                    // if else u is not processed yet or the graph is directed then process the edge (v, u)
                }
            }
        }

        return discoveredList;
    }
}
