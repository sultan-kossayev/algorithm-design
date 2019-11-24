package graph;

import utils.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Finds bridges or cut-edges in a simple undirected graph using
 * recursive dfs
 * <p>
 * Time: O(V + E), Space: O(V)
 */
public class Bridges {

    /**
     * Backward edges are keys to find bridges in a graph, because backward edge always form a cycle in graph
     * and a bridge is viewed as an edge that is not part of a cycle.
     * <p>
     * The more formal definition of a bridge is:
     * an edge (u, v) is a bridge/cut-edge if none of the descendants of v including v itself
     * have a backward edge to u and u's ancestors. It means that there is no other way to get from u to v except edge (u, v)
     * <p>
     * The logic below keeps track of two variables:
     * 1) the discovery time of a vertex u
     * 2) ancestor of vertex u that is reachable from u either by tree or backward edges.
     * <p>
     * Initially, when a vertex u is discovered it gets a discovery time and a reachable ancestor values. The latter is equal to
     * its discovery time.
     * As dfs progresses, whenever we see that a vertex u can reach its ancestor using tree or backward edge
     * we update reachable ancestor for vertex u.
     * Due to this logic, if descendants of u have a backward edge to u then they will have reachable ancestor equal to u
     * (because they can reach u using a backward edge)
     * <p>
     * Thus, it means that an edge (u, v) is a bridge if only if the discovery time of u is less than reachable ancestor of v,
     * because this condition will be true only if v and its descendants don't have a backward edge to u which is a must
     * for considering an edge "non-bridge"
     */
    public List<List<Integer>> find(Graph g) {
        int V = g.numberOfVertices();
        boolean[] discovered = new boolean[V];
        boolean[] processed = new boolean[V];

        int[] discoveredTime = new int[V];
        int[] reachableAncestor = new int[V];

        List<List<Integer>> bridges = new ArrayList<>();

        int time = 0;
        for (int u : g.vertices()) {
            if (!discovered[u]) {
                // initial values for the discovery time and reachable ancestor
                reachableAncestor[u] = discoveredTime[u] = time++;
                discovered[u] = true;

                visitVertex(g, u, -1, time, reachableAncestor, discoveredTime, discovered, processed, bridges);
            }
        }

        return bridges;
    }

    private void visitVertex(Graph g, int u, int parent, int time, int[] reachableAncestor, int[] discoveredTime,
                             boolean[] discovered, boolean[] processed, List<List<Integer>> bridges) {
        for (int v : g.adj(u)) {
            // skip a vertex where we came from
            if (v == parent) {
                continue;
            }

            if (!discovered[v]) {
                // initial values for the discovery time and reachable ancestor
                reachableAncestor[v] = discoveredTime[v] = time++;
                discovered[v] = true;

                visitVertex(g, v, u, time, reachableAncestor, discoveredTime, discovered, processed, bridges);

                // update reachable ancestor using a tree edge (u, v)
                reachableAncestor[u] = Math.min(reachableAncestor[u], reachableAncestor[v]);

                if (discoveredTime[u] < reachableAncestor[v]) {
                    bridges.add(Arrays.asList(u, v));
                }
            } else if (!processed[v]) {
                // update reachable ancestor using a backward edge (u,v).
                // We are using discovery time of v because we are allowed to use at most one backward edge in a chain
                reachableAncestor[u] = Math.min(reachableAncestor[u], discoveredTime[v]);
            }
        }

        processed[u] = true;
    }

}
