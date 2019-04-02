public class Chicken extends Mob {

    public Chicken(Graph.Node currentNode){
        this.currentNode = currentNode;
    }

    @Override
    public void act() {
        System.out.println("quack");
    }

    @Override
    public void move() {
        moveRandom();
    }

    @Override
    public boolean attackPlayer(Player player) {
        System.out.println("The chicken gets distracted by seeds.");
        return false;
    }
}
