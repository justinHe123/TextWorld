public class TakeCommand implements Command{
    Player p;
    String itemName;

    public TakeCommand(Player p, String itemName) {
        this.p = p;
        this.itemName = itemName;
    }

    @Override
    public void init(Player p, String itemName) {
        this.p = p;
        this.itemName = itemName;
    }

    @Override
    public boolean execute() {
        Level.Room currentRoom = p.getCurrentRoom();
        Item i = currentRoom.removeItem(itemName);
        if (i == null) return false;
        p.addItem(i);
        return true;
    }
}
