package node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Node {

    private String name;

    public Node(String name) {
        this.name = name;
    }

    private List<Edge> edges = new ArrayList<>();

    public void addTarget(Node target, int cost) {
        edges.add(new Edge(target, cost));
    }

    public boolean canReach(Node destination) {
        return search(destination, Path.FEWEST_HOPS, new HashSet<>()) != null;
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

        Path path = search(destination, Path.FEWEST_HOPS, new HashSet<>());
        if (path == null) {
            throw new IllegalArgumentException(destination + " is not reachable");
        }
        return path.cumulatedHops();
    }

    public Path path(Node destination) {
        Path path = search(destination, Path.LEAST_COST, new HashSet<>());
        if (path == null) {
            throw new IllegalStateException("not found");
        }
        return path;
    }

    Path search(Node destination, Comparator<Path> comparator, Set<Node> visited) {
        if (this.equals(destination)) {
            return new Path();
        }
        if (visited.contains(this)) {
            return null;
        }
        Set<Node> visited2 = new HashSet<>(visited);
        visited2.add(this);

        List<Path> results = new ArrayList<>();
        for (Edge edge : edges) {
            results.add(edge.traverse(destination, visited2, comparator));
        }
        return results
                .stream()
                .filter(Objects::nonNull)
                .min(comparator)
                .orElse(null);
    }

    public double cost(Node destination) {
        Path path = search(destination, Path.LEAST_COST, new HashSet<>());
        if (path == null) {
            throw new IllegalArgumentException(destination + " is not reachable");
        }
        return path.cumulatedCosts();
    }


    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                '}';
    }
}
