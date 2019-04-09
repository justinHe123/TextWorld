public class DropCommand implements Command {
    Player p;
    String itemName;

    @Override
    public void init(Player p, String itenName) {
        this.p = p;
        this.itemName = itemName;
    }

    @Override
    public boolean execute() {
        Level.Room currentRoom = p.getCurrentRoom();
        currentRoom.addItem(p.removeItem(itemName));
        return false;
    }
}
