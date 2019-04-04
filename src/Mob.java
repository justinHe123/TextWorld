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
        for (Graph.Node node : currentNode.getNeighbors()){
            if (node == p.getCurrentRoom()) return node;
        }
        return null;
    }

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
