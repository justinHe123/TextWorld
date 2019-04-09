public class DropCommand implements Command {
    Player p;
    String itemName;

    public DropCommand(Player p, String itenName) {
        this.p = p;
        this.itemName = itemName;
    }

    @Override
    public void init(Player p, String itenName) {
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
