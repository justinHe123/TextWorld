public class PopStar extends Mob {

    public PopStar(Graph.Node currentNode){
        this.currentNode = currentNode;
        name = "Pop Star";
        description = "死死死死死";
    }

    @Override
    public void move() {
    }

    public Graph.Node findPlayer(Player p) {
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
