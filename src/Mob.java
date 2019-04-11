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
        Level.Room pRoom = p.getCurrentRoom();
        List<Level.Room> pNeighbors = pRoom.getNeighbors();
        List<Level.Room> neighbors = currentRoom.getNeighbors();
        if (neighbors.contains(pRoom)) return pRoom;
        for (Level.Room room : neighbors){
            if (pNeighbors.contains(room)) return room;
        }
        return null;
    }

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
