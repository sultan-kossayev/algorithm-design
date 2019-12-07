package utils;

import java.util.List;
import java.util.stream.Collectors;

public class Edge {

    public int u;
    public int v;

    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }

    public static List<Edge> from(List<List<Integer>> edges) {
        return edges.stream()
                .map(e -> new Edge(e.get(0), e.get(1)))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Edge)) {
            return false;
        }

        Edge e = (Edge) o;

        return this.u == e.u && this.v == e.v;
    }

    @Override
    public String toString() {
        return "(" + this.u + ", " + this.v + ")";
    }
}
