public class LookCommand implements Command {
    Player p;

    public LookCommand() {
    }

    @Override
    public void init(Player p, String itemName) {
        this.p = p;
    }

    @Override
    public boolean execute() {
        Level.Room currentRoom = p.getCurrentRoom();
        System.out.print("You hear: ");
        for (Mob mob : currentRoom.getMobs()) {
            mob.act();
        }
        System.out.println("\nDESCRIPTION: " + currentRoom.getDescription());
        System.out.println("ITEMS: " + currentRoom.displayItems());
        System.out.println("YOU CAN GO TO: " + currentRoom.getNeighborNames());
        return false;
    }
}
