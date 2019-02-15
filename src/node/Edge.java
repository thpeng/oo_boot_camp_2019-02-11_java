package node;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

class Edge {
    private Node to;
    private double cost;

    Edge( Node to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    Path traverse(Node destination, Set<Node> visited, Comparator<Path> comparator) {
        Path result = to.search(destination, comparator, visited);
        if (result != null) {
            return result.addEdge(this);
        }
        return null;
    }
    static double totalCost(List<Edge> edges){
        return edges.stream().mapToDouble(edge -> edge.cost).sum();
    }

    @Override
    public String toString() {
        return "Edge{" +
                "cost=" + cost +
                '}';
    }
}
