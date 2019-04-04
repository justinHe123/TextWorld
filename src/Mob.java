import java.util.*;

public abstract class Mob {
    protected Graph.Node currentNode;
    protected String name, description;

    public Graph.Node getCurrentNode(){
        return this.currentNode;
    };

    public void moveTo(Graph.Node n){
        currentNode.removeMob(this);
        currentNode = n;
        currentNode.addMob(this);
    }

    public Graph.Node findPlayer(Player p){
        List<Graph.Node> visited = new ArrayList<>();
        List<Graph.Node> unvisitedNeighbors = new ArrayList<>();
        if (currentNode == p.getCurrentRoom()) return currentNode;
        visited.add(currentNode);

        for (Graph.Node node : currentNode.getNeighbors()){
            if (!visited.contains(node)) {
                if (node == p.getCurrentRoom()) return node;
            } else {
                visited.add(node);
                unvisitedNeighbors.add(node);
            }
        }

        return findPlayer(p, visited, unvisitedNeighbors);
    }

    public Graph.Node findPlayer(Player p, List<Graph.Node> visited, List<Graph.Node> toVisit){
        List<Graph.Node> unvisitedNeighbors = new ArrayList<>();
        for (Graph.Node nA : toVisit){
            for (Graph.Node nB : nA.getNeighbors()){
                if (!visited.contains(nB)){
                    if (nB == p.getCurrentRoom()) return nB;
                } else {
                    visited.add(nB);
                    unvisitedNeighbors.add(nB);
                }
            }
        }
        if (unvisitedNeighbors.size() == 0) return null;
        else return findPlayer(p, visited, unvisitedNeighbors);
    }
    //how do we track the path?

    public void moveRandom(){
        System.out.println("This method works");
        List<Graph.Node> neighbors = currentNode.getNeighbors();
        if (neighbors.size() != 0) {
            moveTo(currentNode.getRandomNeighbor());
        }
        System.out.println("MOB MOVED TO: " + currentNode.getName());
    }

    public abstract void move();
    public abstract void act();
    public abstract boolean attackPlayer(Player player);
}
