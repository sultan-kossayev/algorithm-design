package graph;

import utils.Edge;
import utils.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Finds bridges in a given graph
 *
 * Time: O(V + E), Space: O(V)
 */
public class Bridges {

    // bridges aka cut-edges
    private List<Edge> bridges;

    // the discovery time of a vertex.
    // By default contains all zeros which means that a vertex hasn't been discovered yet
    private int[] discoveryTime;

    // the earliest ancestor that is reachable by a vertex. By default, the earliest ancestor is a vertex itself
    private int[] ancestor;

    // every time when a vertex is discovered, this counter gets simply incremented
    private int currentTime = 0;


    public Bridges(Graph g) {
        int V = g.numberOfVertices();

        bridges = new ArrayList<>(V);
        discoveryTime = new int[V];
        ancestor = new int[V];

        dfs(g);
    }

    private void dfs(Graph g) {
        for (int u : g.vertices()) {
            if (discoveryTime[u] == 0) {
                visitVertex(g, u, -1);
            }
        }
    }

    private void visitVertex(Graph g, int u, int parent) {
        // set ancestor and discovery time of u
        ancestor[u] = discoveryTime[u] = ++currentTime;

        for (int v : g.adj(u)) {

            // skip a vertex that discovered u
            // relevant only for undirected graphs
            if (v == parent) {
                continue;
            }

            if (discoveryTime[v] == 0) {
                // set ancestor and discovery time of v
                visitVertex(g, v, u);

                // update the ancestor that is reachable by u
                // this makes sense if v can reach the ancestors of u somewhere along dfs tree
                ancestor[u] = Math.min(ancestor[u], ancestor[v]);

                // (u, v) is a bridge because v or v's descendants
                // can't reach u or u's ancestors
                if (discoveryTime[u] < ancestor[v]) {
                    bridges.add(new Edge(u, v));
                }
            } else {
                // this is a case when (u, v) is backward edge
                // thus we should try to update reachable ancestor of u
                // we are using discovery time of v and not v's ancestor because we are allowed
                // to use at most one backward edge in a chain
                ancestor[u] = Math.min(ancestor[u], discoveryTime[v]);
            }
        }
    }

    public List<Edge> bridges() {
        return bridges;
    }
}
