package graph;

import utils.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Finds articulation points in a given graph
 *
 * Time O(V + E), space O(V)
 */
public class ArticulationPoints {

    // articulation points
    private List<Integer> cutNodes;

    // the discovery time of a vertex.
    // By default contains all zeros which means that a vertex hasn't been discovered yet
    private int[] discoveryTime;

    // the earliest ancestor that is reachable by a vertex. By default, the earliest ancestor is a vertex itself
    private int[] ancestor;

    // every time when a vertex is discovered, this counter gets simply incremented
    private int currentTime = 0;

    // the number of outgoing edges that the root of the dfs tree (not graph!) has
    private int rootOutDegree = 0;

    public ArticulationPoints(Graph g) {
        int V = g.numberOfVertices();

        cutNodes = new ArrayList<>(V);
        discoveryTime = new int[V];
        ancestor = new int[V];

        dfs(g);
    }

    private void dfs(Graph g) {
        for (int u : g.vertices()) {
            if (discoveryTime[u] == 0) {

                rootOutDegree = 0;

                visitVertex(g, u, -1, u);

                if (rootOutDegree > 1) {
                    // u is a root cut-node
                    cutNodes.add(u);
                }
            }
        }
    }

    private void visitVertex(Graph g, int u, int parent, int root) {
        // set ancestor and discovery time of u
        ancestor[u] = discoveryTime[u] = ++currentTime;

        // check whether u is a direct child of the root in the dfs tree
        if (parent == root) {
            ++rootOutDegree;
        }

        for (int v : g.adj(u)) {
            // skip a vertex that discovered u before
            // this is relevant only for undirected graphs
            if (v == parent) {
                continue;
            }

            if (discoveryTime[v] == 0) {

                // set ancestor and discovery time of v
                visitVertex(g, v, u, root);

                // update the ancestor that is reachable by u
                // this makes sense if v can reach the ancestors of u somewhere along dfs tree
                ancestor[u] = Math.min(ancestor[u], ancestor[v]);

                // u is a bridge cut-node if v can't reach the ancestors of u
                // u is a parent cut-node if v can only reach u itself and not ancestors of u
                // u can't be a root and at the same time bridge or parent cut-node
                if (root != u && (discoveryTime[u] < ancestor[v] || discoveryTime[u] == ancestor[v])) {
                    cutNodes.add(u);
                }

            } else {
                // this is a case when (u, v) is backward edge
                // thus we should try to update reachable ancestor of u
                // We are using discovery time of v and not v's ancestor because we are allowed
                // to use at most one backward edge in a chain
                ancestor[u] = Math.min(ancestor[u], discoveryTime[v]);
            }
        }
    }

    public List<Integer> points() {
        return cutNodes;
    }
}

