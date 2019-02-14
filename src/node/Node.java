package node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Node {

    private String name;

    public Node(String name) {
        this.name = name;
    }

    private List<Node> targets = new ArrayList<>();

    public void addTarget(Node... other) {
        targets.addAll(Arrays.asList(other));
    }

    public boolean canReach(Node destination) {
        return hopCount(destination) != -1;
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

    public int hopCount(Node b) {
        return hopCount(b, new HashSet<>());
    }

    private int hopCount(Node destination, Set<Node> visited) {
        if (this.equals(destination)) {
            return 0;
        }
        if (visited.contains(this)) {
            return -1;
        }
        visited.add(this);

        /*return targets.stream()
                .map(node -> node.hopCount(node, visited))
                .filter(res -> res != -1)
                .findFirst()
                .map(hops -> hops +1)
                .orElse(-1);*/
        //conservative approach
        for (Node target : targets) {
            int result = target.hopCount(destination, visited);
            if (result != -1) {
                return result + 1;
            }
        }

        return -1;
    }
}
