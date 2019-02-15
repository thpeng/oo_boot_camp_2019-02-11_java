package node;

import java.util.List;
import java.util.Set;

class Edge {
    private Node to;
    private double cost;

    interface EdgeSumStrategy {
        double sumUp(List<Edge> edges);
    }

    static EdgeSumStrategy costStrategy() {
        return edges -> edges.stream().mapToDouble(edge -> edge.cost).sum();
    }

    static EdgeSumStrategy hopStrategy() {
        return edges -> edges.size();
    }

    Edge( Node to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    Path traverse(Node destination, Set<Node> visited, EdgeSumStrategy strategy) {
        Path result = to.search(destination, strategy, visited);
        if (result != null) {
            return result.addEdge(this);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "cost=" + cost +
                '}';
    }
}
