public class DropCommand implements Command {
    Player p;
    String itemName;

    public DropCommand() {
    }

    @Override
    public void init(Player p, String itemName) {
        this.p = p;
        this.itemName = itemName;
    }

    @Override
    public boolean execute() {
        Level.Room currentRoom = p.getCurrentRoom();
        Item i = p.removeItem(itemName);
        if (i == null) return false;
        currentRoom.addItem(i);
        return true;
    }
}
