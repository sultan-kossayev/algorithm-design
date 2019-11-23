package graph;

import utils.Graph;

import java.util.*;

/**
 * Finds a cycle in a simple undirected graph
 * If the graph has multiple cycle, only the first found will be returned
 * <p>
 * Time: O(E+V), Space: O(V)
 */
public class CyclicPath {

    /**
     * The below logic is a standard dfs iterative implementation.
     * Inline comments show lines that are key for finding the cyclic path.
     */
    public List<Integer> find(Graph g) {
        // the map stores vertex and its parent that discovered it
        // key - vertex, value - parent
        Map<Integer, Integer> seen = new HashMap<>();

        // the last vertex that closes the cycle
        Integer last = null;
        for(int x : g.vertices()) {
            // once a cycle is found, we can stop the search
            if (last != null) {
                break;
            }
            if (!seen.containsKey(x)) {
                seen.put(x, x);

                Deque<Integer> stack = new LinkedList<>();
                stack.push(x);
                while(!stack.isEmpty()) {
                    // once a cycle is found, we can stop the search
                    if (last != null) {
                        break;
                    }

                    int v = stack.peek();

                    boolean processed = true;
                    for (int u : g.adj(v)) {
                        if (!seen.containsKey(u)) {
                            stack.push(u);
                            seen.put(u, v);
                            processed = false;
                            break;
                        } else if (seen.get(v) != u) {

                            // the cycle is found
                            last = v;
                            break;
                        }
                    }

                    if (processed) {
                        stack.pop();
                    }
                }
            }
        }

        if (last != null) {
            Deque<Integer> path = new LinkedList<>();
            // the path is constructed going backward
            for (Integer v = last; v != seen.get(v); v = seen.get(v)) {
                path.push(v);
            }

            return new ArrayList<>(path);
        } else {
            return new ArrayList<>();
        }
    }
}
