import java.util.List;

public class Wumpus extends Mob {
    Player p;

    public Wumpus(Level.Room currentRoom, Player p){
        this.currentRoom = currentRoom;
        name = "Wumpus";
        description = "死死死死";
        this.p = p;
    }

    @Override
    public void move() {
        Level.Room sharedRoom = findPlayer(p);
        List<Level.Room> neighbors = currentRoom.getNeighbors();
        if (sharedRoom != null && neighbors.size() > 1) {
            boolean moved = false;
            while (!moved){
                Level.Room randomNeighbor = currentRoom.getRandomNeighbor();
                if (randomNeighbor != sharedRoom) {
                    moveTo(randomNeighbor);
                    moved = true;
                }
            }
        }
    }

    @Override
    public void act() {
        System.out.println("womp womp womp");
    }

    @Override
    public boolean attackPlayer(Player player) {
        return false;
    }
}
