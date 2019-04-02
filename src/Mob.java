import java.util.*;

public abstract class Mob {
    protected Graph.Node currentNode;
    protected String name, description;

    public Graph.Node getCurrentNode(){
        return this.currentNode;
    };

    public void moveRandom(){
        System.out.println("This method works");
        List<Graph.Node> neighbors = currentNode.getNeighbors();
        if (neighbors.size() != 0) {
            currentNode.removeMob(this);
            currentNode = neighbors.get((int) (Math.random() * neighbors.size()));
            currentNode.addMob(this);
        }
        System.out.println("MOB MOVED TO: " + currentNode.getName());
    }

    public abstract void move();
    public abstract void act();
    public abstract boolean attackPlayer(Player player);
}
