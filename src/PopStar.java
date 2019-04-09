public class PopStar extends Mob {

    public PopStar(Level.Room currentRoom){
        this.currentRoom = currentRoom;
        name = "Pop Star";
        description = "死死死死死";
    }

    @Override
    public void move() {
    }

    public Level.Room findPlayer(Player p) {
        return p.getCurrentRoom();
    }

    @Override
    public void act() {

    }

    @Override
    public boolean attackPlayer(Player player) {
        return false;
    }
}
