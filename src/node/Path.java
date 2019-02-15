package node;

import java.util.ArrayList;
import java.util.List;

public class Path implements Comparable<Path> {
    private Edge.EdgeSumStrategy edgeSumStrategy;
    private final List<Edge> edges = new ArrayList<>();

    Path(Edge.EdgeSumStrategy edgeSumStrategy) {
        this.edgeSumStrategy = edgeSumStrategy;
    }

    Path addEdge(Edge edge) {
        edges.add(edge);
        return this;
    }

    public int cumulatedHops() {
        return (int) Edge.hopStrategy().sumUp(edges);
    }

    public double cumulatedCosts() {
        return Edge.costStrategy().sumUp(edges);
    }

    @Override
    public int compareTo(Path other) {
        return Double.compare( edgeSumStrategy.sumUp(edges),  edgeSumStrategy.sumUp(other.edges));
    }

    @Override
    public String toString() {
        return "Path{" +
                "edges=" + edges +
                '}';
    }
}
