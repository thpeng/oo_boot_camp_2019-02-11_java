package node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Path {

    final static Comparator<Path> LEAST_COST = (p1, p2) -> Double.compare(p1.cumulatedCosts(), p2.cumulatedCosts());
    final static Comparator<Path> FEWEST_HOPS = (p1, p2) -> Integer.compare(p1.cumulatedHops(), p2.cumulatedHops());
    private final List<Edge> edges = new ArrayList<>();

    Path() {
    }

    Path addEdge(Edge edge) {
        edges.add(edge);
        return this;
    }

    public int cumulatedHops() {
        return edges.size();
    }

    public double cumulatedCosts() {
        return Edge.totalCost(edges);
    }

    @Override
    public String toString() {
        return "Path{" +
                "edges=" + edges +
                '}';
    }
}
