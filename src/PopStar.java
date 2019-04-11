public class PopStar extends Mob {
    Player p;


    public PopStar(Level.Room currentRoom, Player p){
        this.currentRoom = currentRoom;
        name = "Pop Star";
        description = "死死死死死";
        this.p = p;
    }

    @Override
    public void move() {
        Level.Room sharedRoom = findPlayer(p);
        if (p != null) moveTo(sharedRoom);
    }

    @Override
    public void act() {
        System.out.println("gimme ur money");
    }

    @Override
    public boolean attackPlayer(Player player) {
        return false;
    }
}
