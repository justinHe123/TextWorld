import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Build up graph of connected nodes
        Graph g = new Graph();
        g.addNode("hall", "You are in a long hallway. You feel the great distance as a void staring deep into your soul.");
        g.addNode("closet", "You are in a cramped closet. Someone needs to do their laundry, but you don't know who.");
        g.addNode("bedroom", "You are in a bedroom. There are no beds.");
        g.addNode("hallway", "You are in a hallway. Like a hall, but a different.");
        g.addNode("definitely not a room", "How did you get here??");
        g.addNode("DO NOT ENTER", "DID YOU READ THE SIGN???");
        g.addUndirectedEdge("hall", "closet");
        g.addUndirectedEdge("hall", "bedroom");
        g.addUndirectedEdge("bedroom", "hallway");
        g.addUndirectedEdge("hallway", "definitely not a room");
        g.addUndirectedEdge("hallway", "DO NOT ENTER");
        g.addUndirectedEdge("DO NOT ENTER", "hall");

        g.getNode("hall").addItem("Key");
        g.getNode("bedroom").addItem("Vase");
        g.getNode("definitely not a room").addItem("GOD KILLER");
        g.getNode("DO NOT ENTER").addItem("Discarded Wrappers");

        g.getNode("bedroom").addMob(new Chicken(g.getNode("bedroom")));

        //"game loop" where I get user input and move the player
        Player p = new Player("Your Boy", "The OG");
        p.setCurrentRoom(g.getNode("hall"));

        String response = "";
        Scanner in = new Scanner(System.in);

        do{
            //display the room and the exits
            try {
                Graph.Node currentRoom = p.getCurrentRoom();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("You are currently in the " + currentRoom.getName());
                System.out.println("What would you like to do?");
                System.out.println("COMMANDS: go, stay, look, add, link, take, drop, quit");

                response = in.nextLine();
                String[] words = response.split(" ");
                String firstWord = words[0];
                String remainingWords = combineWithSpace(words, 1);

                if (firstWord.equals("go")) { //find a way to get all mobs to act
                    g.moveAllMobs();
                    if(!p.moveToRoom(combineWithSpace(words, 1))) System.err.println("Something went wrong!");
                } else if (firstWord.equals("stay")){
                    g.moveAllMobs();
                }
                else if (firstWord.equals("look")) {
                    System.out.print("You hear: " );
                    for (Mob mob : currentRoom.getMobs()){
                        mob.act();
                    }
                    System.out.println("\nDESCRIPTION: " + currentRoom.getDescription());
                    System.out.println("ITEMS: " + currentRoom.displayItems());
                    System.out.println("YOU CAN GO TO: " + currentRoom.getNeighborNames());
                }
                else if (firstWord.equals("add")) currentRoom.addNeighbor(g.addNode(remainingWords));
                else if (firstWord.equals("link")) currentRoom.addNeighbor(g.getNode(remainingWords));
                else if (firstWord.equals("take")){
                    p.addItem(currentRoom.removeItem(remainingWords));
                    System.out.println(remainingWords + " has been taken.");
                }
                else if (firstWord.equals("drop")){
                    currentRoom.addItem(p.removeItem(remainingWords));
                    System.out.println(remainingWords + " has been dropped.");
                }
                else if (firstWord.equals("quit")) continue;
                else System.out.println("INVALID COMMAND");

            } catch (Exception e){
                System.err.println("Something went wrong!");
                e.printStackTrace();
            }
        } while (!response.equals("quit"));
    }

    public static String combineWithSpace(String[] words, int start){
        String s = "";
        for (int i = start; i < words.length; i++){
            s += words[i] + " ";
        }
        return s.trim();
    }
}
