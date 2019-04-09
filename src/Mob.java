import java.util.*;

public abstract class Mob {
    protected Level.Room currentRoom;
    protected String name, description;

    public Level.Room getCurrentRoom(){
        return this.currentRoom;
    };

    public void moveTo(Level.Room n){
        currentRoom.removeMob(this);
        currentRoom = n;
        currentRoom.addMob(this);
    }

    public Level.Room findPlayer(Player p){
        List<Level.Room> visited = new ArrayList<>();
        List<Level.Room> unvisitedNeighbors = new ArrayList<>();
        if (currentRoom == p.getCurrentRoom()) return currentRoom;
        visited.add(currentRoom);

        for (Level.Room room : currentRoom.getNeighbors()){
            if (!visited.contains(room)) {
                if (room == p.getCurrentRoom()) return room;
            } else {
                visited.add(room);
                unvisitedNeighbors.add(room);
            }
        }

        return findPlayer(p, visited, unvisitedNeighbors);
    }

    public Level.Room findPlayer(Player p, List<Level.Room> visited, List<Level.Room> toVisit){
        List<Level.Room> unvisitedNeighbors = new ArrayList<>();
        for (Level.Room nA : toVisit){
            for (Level.Room nB : nA.getNeighbors()){
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
        List<Level.Room> neighbors = currentRoom.getNeighbors();
        if (neighbors.size() != 0) {
            moveTo(currentRoom.getRandomNeighbor());
        }
        System.out.println("MOB MOVED TO: " + currentRoom.getName());
    }

    public abstract void move();
    public abstract void act();
    public abstract boolean attackPlayer(Player player);
}
