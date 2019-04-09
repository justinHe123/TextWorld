public class Chicken extends Mob {

    public Chicken(Level.Room currentRoom){
        this.currentRoom = currentRoom;
        name = "Chicken";
        description = "A small, fat bird.";
    }

    @Override
    public void act() {
        System.out.print("quack ");
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
