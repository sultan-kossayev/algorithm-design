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

    public List<Integer> search(Graph g) {
        List<Integer> res = new ArrayList<>(); // needed only for test cases
        boolean[] discovered = new boolean[g.numberOfVertices()];

        for (int x : g.vertices()) {
            if (discovered[x]) {
                continue;
            }

            Queue<Integer> q = new LinkedList<>();
            q.offer(x);
            discovered[x] = true;

            while (!q.isEmpty()) {
                int v = q.poll();

                // process the vertex v early
                res.add(v);

                for (int u : g.adj(v)) {
                    if (!discovered[u]) {
                        // process the edge (v, u)

                        // discover the vertex u
                        discovered[u] = true;

                        // go process the vertex u
                        q.offer(u);
                    }
                    // if else u is not processed yet or the graph is directed then process the edge (v, u)
                }

                // process the vertex v late
                // mark the vertex v processed
            }
        }

        return res;
    }
}
