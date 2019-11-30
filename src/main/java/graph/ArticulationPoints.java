package graph;

import utils.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Finds articulation points in a graph
 * Time O(V + E), space O(V)
 * <p>
 * The logic to find articulation points aka cut nodes is similar to what is described in {@link Bridges}.
 * <p>
 * There are 3 types of cut nodes: root, bridge, and parent
 * - root cut node is a vertex that is the root of the dfs tree and it has out-degree greater than 1
 * - bridge cut node is u vertex in (u, v) edge where the discovery time of u vertex is less
 * than the discovery time of an ancestor that v is able to reach
 * - parent cut node is u vertex in (u, v) edge where the discovery time of u vertex is equal to
 * the discovery time of an ancestor that v is able to reach
 * <p>
 * Useful resources:
 * 1) https://www.youtube.com/watch?v=aZXi1unBdJA gives a nice overview of articulation points
 * 2) Skiena's book on page 176 describes 3 types of articulation points
 * 3) https://www.hackerearth.com/practice/algorithms/graphs/articulation-points-and-bridges/tutorial/ explains
 * the intuition behind the algorithm explained in the above video
 */
public class ArticulationPoints {

    // every time when a vertex is discovered, this counter gets incremented
    // it should be global to keep track the time for the whole graph
    private int currentTime = 0;

    // the number of outgoing edges that the root of the dfs tree has
    private int rootOutDegree = 0;

    public List<Integer> find(Graph g) {
        int V = g.numberOfVertices();

        // discovery time of a vertex. Zero value means a vertex hasn't been discovered yet
        int[] discoveryTime = new int[V];
        // earliest ancestor that is reachable by a vertex. By default, that ancestor is a vertex itself
        int[] ancestor = new int[V];

        // articulation points
        List<Integer> cutNodes = new ArrayList<>();

        for (int u : g.vertices()) {
            if (discoveryTime[u] == 0) {
                discoveryTime[u] = ancestor[u] = ++currentTime;
                rootOutDegree = 0;
                visitVertex(g, u, -1, u, discoveryTime, ancestor, cutNodes);
                if (rootOutDegree > 1) {
                    // u is a root cut-node
                    cutNodes.add(u);
                }
            }
        }

        return cutNodes;
    }

    private void visitVertex(Graph g, int u, int parent, int root, int[] discoveryTime, int[] ancestor,
                             List<Integer> cutNodes) {

        // u is a direct child of the root in the dfs tree
        if (parent == root) {
            ++rootOutDegree;
        }

        for (int v : g.adj(u)) {
            // skip a vertex that discovered u
            if (v == parent) {
                continue;
            }

            if (discoveryTime[v] == 0) {
                ancestor[v] = discoveryTime[v] = ++currentTime;

                visitVertex(g, v, u, root, discoveryTime, ancestor, cutNodes);

                /** logic is the same as in {@link Bridges} */
                ancestor[u] = Math.min(ancestor[u], ancestor[v]);

                // u is a bridge cut-node OR u is a parent cut-node. Also u can't be a root cut-node
                if (root != u && (discoveryTime[u] < ancestor[v] || discoveryTime[u] == ancestor[v])) {
                    cutNodes.add(u);
                }
            } else {
                /** logic is the same as in {@link Bridges } */
                ancestor[u] = Math.min(ancestor[u], discoveryTime[v]);
            }
        }
    }
}

