package node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Node {

    private String name;

    public Node(String name) {
        this.name = name;
    }

    private List<Edge> edges = new ArrayList<>();

    public void addTarget(Node... other) {
        edges.addAll(Arrays.stream(other)
                .map(node -> new Edge(this, node, 1))
                .collect(Collectors.toList()));
    }

    public void addTarget(Node target, int cost) {
        edges.add(new Edge(this, target, cost));
    }

    public boolean canReach(Node destination) {
        return hopCount(destination,  new HashSet<>()) != Double.POSITIVE_INFINITY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public int hopCount(Node destination) {
        double hopCount = hopCount(destination, new HashSet<>());
        if(hopCount == Double.POSITIVE_INFINITY) {
            throw new IllegalArgumentException(destination + " is not reachable");
        }
        return (int) hopCount;
    }

    double hopCount(Node destination, Set<Node> visited) {
        if (this.equals(destination)) {
            return 0;
        }
        if (visited.contains(this)) {
            return Double.POSITIVE_INFINITY;
        }
        Set<Node> visited2 = new HashSet<>(visited);
        visited2.add(this);

        return edges.stream()
                .mapToDouble(edge -> edge.hopCount(destination, visited2))
                .filter(res -> res != Double.POSITIVE_INFINITY)
                .min()
                .orElse(Double.POSITIVE_INFINITY);
    }

    public double cost(Node destination) {
        double cost = cost(destination, new HashSet<>());
        if(cost == Double.POSITIVE_INFINITY) {
            throw new IllegalArgumentException(destination + " is not reachable");
        }
        return cost;
    }

    double cost(Node destination, Set<Node> visited) {
        if (this.equals(destination)) {
            return 0;
        }
        if (visited.contains(this)) {
            return Double.POSITIVE_INFINITY;
        }
        Set<Node> visited2 = new HashSet<>(visited);
        visited2.add(this);

        return edges.stream()
                .mapToDouble(edge -> edge.cost(destination, visited2))
                .filter(res -> res != Double.POSITIVE_INFINITY)
                .min()
                .orElse(Double.POSITIVE_INFINITY);
    }
}
