package node;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Candidates {
    final static Comparator<Path> COST = Comparator.comparingDouble(Path::cost);
    final static Comparator<Path> HOPS = Comparator.comparingInt(Path::hops);

    static Candidates createEmpty() {
        return new Candidates(Collections.emptyList());
    }

    static Candidates create() {
        return new Candidates(Collections.singletonList(new Path()));
    }

    private final List<Path> paths;

    private Candidates(List<Path> paths) {
        this.paths = paths;
    }

    Candidates merge(Candidates candidates) {
        List<Path> collectedPaths = Stream
                .concat(paths.stream(), candidates.paths.stream())
                .collect(Collectors.toList());
        return new Candidates(collectedPaths);
    }

    public Path shortest() {
        return paths.stream().min(HOPS).get();
    }

    public Path cheapest() {
        return paths.stream().min(COST).get();
    }

    public Path longest() {
        return paths.stream().max(HOPS).get();
    }

    public Path mostExepensive() {
        return paths.stream().max(COST).get();
    }

    Candidates addEdge(Edge edge) {
        paths.stream().forEach(path -> path.addEdge(edge));
        return this;
    }

    boolean isEmpty() {
        return paths.isEmpty();
    }
}
