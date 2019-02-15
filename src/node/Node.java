package node;

import java.util.ArrayList;
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
        return !search(destination, new HashSet<>()).isEmpty();
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

        Candidates candidates = search(destination, new HashSet<>());
        if (candidates.isEmpty()) {
            throw new IllegalArgumentException(destination + " is not reachable");
        }
        return candidates.shortest().hops();
    }

    public Path path(Node destination) {
        Candidates candidates = search(destination, new HashSet<>());
        if (candidates.isEmpty()) {
            throw new IllegalStateException("not found");
        }
        return candidates.cheapest();
    }

    Candidates search(Node destination, Set<Node> visited) {
        if (this.equals(destination)) {
            return Candidates.create();
        }
        if (visited.contains(this)) {
            return Candidates.createEmpty();
        }
        Set<Node> visited2 = new HashSet<>(visited);
        visited2.add(this);
        return edges.stream()
                .map(edge -> edge.traverse(destination, visited2))
                .reduce(Candidates.createEmpty(), (result, candidate) -> result.merge(candidate));
    }

    public double cost(Node destination) {
        Candidates candidates = search(destination, new HashSet<>());
        if (candidates.isEmpty()) {
            throw new IllegalArgumentException(destination + " is not reachable");
        }
        return candidates.cheapest().cost();
    }


    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                '}';
    }

    public Candidates paths(Node destination) {
        return search(destination, new HashSet<>());
    }
}
