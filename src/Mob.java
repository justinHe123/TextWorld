import java.util.*;

public abstract class Mob {
    protected Graph.Node currentNode;

    public Graph.Node getCurrentNode(){
        return this.currentNode;
    };

    public void moveRandom(){
        List<Graph.Node> neighbors = currentNode.getNeighbors();
        currentNode = neighbors.get((int)(Math.random() * neighbors.size()));
    }

    public abstract void move();
    public abstract void act();
    public abstract boolean attackPlayer(Player player);
}
