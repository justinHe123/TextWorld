public class GoCommand implements Command {
    Player p;
    String destination;

    public GoCommand(){
    }

    @Override
    public void init(Player p, String destination) {
        this.p = p;
        this.destination = destination;
    }

    @Override
    public boolean execute() {
        boolean moved = p.moveToRoom(destination);
        if (!moved) System.err.println("You can't move there!");
        return moved;
    }
}
