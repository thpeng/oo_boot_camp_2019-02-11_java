package node;

import java.util.ArrayList;
import java.util.List;

public class Path {


    private final List<Edge> edges = new ArrayList<>();

    Path() {
    }

    Path addEdge(Edge edge) {
        edges.add(edge);
        return this;
    }

    public int hops() {
        return edges.size();
    }

    public double cost() {
        return Edge.totalCost(edges);
    }

    @Override
    public String toString() {
        return "Path{" +
                "edges=" + edges +
                '}';
    }
}
