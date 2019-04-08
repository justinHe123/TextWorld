import java.util.*;

public class DijkstraAlgorithm {

    private final List<Graph.Node> nodes;
    private Set<Graph.Node> settledNodes;
    private Set<Graph.Node> unSettledNodes;
    private Map<Graph.Node, Graph.Node> predecessors;
    private Map<Graph.Node, Integer> distance;

    public DijkstraAlgorithm(Graph graph) {
        this.nodes = new ArrayList<Graph.Node>(graph.getNodes());
    }

    public void execute(Graph.Node source) {
        settledNodes = new HashSet<Graph.Node>();
        unSettledNodes = new HashSet<Graph.Node>();
        distance = new HashMap<Graph.Node, Integer>();
        predecessors = new HashMap<Graph.Node, Graph.Node>();

        distance.put(source, 0);
        unSettledNodes.add(source);

        while (unSettledNodes.size() > 0) {
            Graph.Node n = getMinimum(unSettledNodes);
            settledNodes.add(n);
            unSettledNodes.remove(n);
            findMinimalDistances(n);
        }
    }

    private void findMinimalDistances(Graph.Node node) {
        List<Graph.Node> neighbors = new ArrayList<>(node.getNeighbors());
        for (Graph.Node target : neighbors) {
            if (getShortestDistance(target) > getShortestDistance(node)) {
                distance.put(target, getShortestDistance(node));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }
    }


    private Graph.Node getMinimum(Set<Graph.Node> nodes) {
        Graph.Node minimum = null;
        for (Graph.Node n : nodes) {
            if (minimum == null) {
                minimum = n;
            } else if (getShortestDistance(n) < getShortestDistance(minimum)) {
                minimum = n;
            }
        }
        return minimum;
    }

    private int getShortestDistance(Graph.Node n) {
        Integer d = distance.get(n);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    private boolean isSettled(Graph.Node n) {
        return settledNodes.contains(n);
    }
}
