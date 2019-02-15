package node;

import java.util.List;
import java.util.Set;

class Edge {
    private Node to;
    private double cost;

    Edge(Node to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    Candidates traverse(Node destination, Set<Node> visited) {
        return to.search(destination, visited).addEdge(this);
    }

    static double totalCost(List<Edge> edges) {
        return edges.stream().mapToDouble(edge -> edge.cost).sum();
    }

    @Override
    public String toString() {
        return "Edge{" +
                "cost=" + cost +
                '}';
    }
}
