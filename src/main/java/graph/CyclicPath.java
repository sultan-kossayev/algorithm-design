package graph;

import java.util.*;

public class CyclicPath {



    public List<Integer> find(utils.Graph g) {
        // key - vertex, value - parent
        Map<Integer, Integer> seen = new HashMap<>();
        Integer first = null;
        for(int x : g.vertices()) {
            if (first != null) {
                break;
            }
            if (!seen.containsKey(x)) {
                seen.put(x, x);

                Deque<Integer> stack = new LinkedList<>();
                stack.push(x);
                while(!stack.isEmpty()) {
                    if (first != null) {
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
                            first = v;
                            break;
                        }
                    }

                    if (processed) {
                        stack.pop();
                    }
                }
            }
        }


        if (first != null) {
            Deque<Integer> path = new LinkedList<>();
            for (Integer v = first; v != seen.get(v); v = seen.get(v)) {
                path.push(v);
            }

            return new ArrayList<>(path);
        } else {
            return new ArrayList<>();
        }
    }
}
