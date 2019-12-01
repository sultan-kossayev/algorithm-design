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
 *
 * Useful resources:
 * 1) https://www.youtube.com/watch?v=aZXi1unBdJA gives a nice overview of articulation points
 * 2) https://www.hackerearth.com/practice/algorithms/graphs/articulation-points-and-bridges/tutorial/ explains
 * the intuition behind the algorithm explained in the above video
 */
public class Bridges {

    private int currentTime = 0;

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

        // discovery time of a vertex. Zero value means a vertex hasn't been discovered yet
        int[] discoveryTime = new int[V];
        // earliest ancestor that is reachable by a vertex. By default, that ancestor is a vertex itself
        int[] ancestor = new int[V];

        List<List<Integer>> bridges = new ArrayList<>();

        for (int u : g.vertices()) {
            if (discoveryTime[u] == 0) {
                // initial values for the discovery time and reachable ancestor
                ancestor[u] = discoveryTime[u] = ++currentTime;

                visitVertex(g, u, -1, ancestor, discoveryTime, bridges);
            }
        }

        return bridges;
    }

    private void visitVertex(Graph g, int u, int parent, int[] ancestor, int[] discoveryTime,
                             List<List<Integer>> bridges) {
        for (int v : g.adj(u)) {

            // skip a vertex that discovered u
            if (v == parent) {
                continue;
            }

            if (discoveryTime[v] == 0) {
                // initial values for the discovery time and reachable ancestor
                ancestor[v] = discoveryTime[v] = ++currentTime;

                visitVertex(g, v, u, ancestor, discoveryTime, bridges);

                // update reachable ancestor using a tree edge (u, v)
                ancestor[u] = Math.min(ancestor[u], ancestor[v]);

                if (discoveryTime[u] < ancestor[v]) {
                    bridges.add(Arrays.asList(u, v));
                }
            } else {
                // update reachable ancestor using a backward edge (u,v).
                // We are using discovery time of v because we are allowed to use at most one backward edge in a chain
                ancestor[u] = Math.min(ancestor[u], discoveryTime[v]);
            }
        }
    }

}
