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
        g.addDirectedEdge("hall", "bedroom");
        g.addDirectedEdge("bedroom", "hallway");
        g.addDirectedEdge("hallway", "definitely not a room");
        g.addDirectedEdge("hallway", "DO NOT ENTER");
        g.addDirectedEdge("DO NOT ENTER", "hall");


        //"game loop" where I get user input and move the player

        Graph.Node currentRoom = g.getNode("hall");
        Graph.Node prevRoom = currentRoom;

        String response = "";
        Scanner in = new Scanner(System.in);

        do{
            //display the room and the exits
            try {
                System.out.println("You are currently in the " + currentRoom.getName());
                System.out.println("What would you like to do?");
                System.out.println("COMMANDS: go, look, add, link, quit");
                response = in.nextLine();
                String[] words = response.split(" ");
                String firstWord = words[0];
                if (firstWord.equals("go")) {
                    prevRoom = currentRoom;
                    currentRoom = currentRoom.getNeighbor(combineWithSpace(words, 1));
                }
                else if (firstWord.equals("look")) {
                    System.out.println("DESCRIPTION: " + currentRoom.getDescription());
                    System.out.println("YOU CAN GO TO: " + currentRoom.getNeighborNames());
                }
                else if (firstWord.equals("add")) currentRoom.addNeighbor(g.addNode(combineWithSpace(words, 1)));
                else if (firstWord.equals("link")) currentRoom.addNeighbor(g.getNode(combineWithSpace(words, 1)));
                else if (firstWord.equals("quit")) continue;
                else System.out.println("INVALID COMMAND");
            } catch (Exception e){
                System.err.println("Something went wrong!");
                currentRoom = prevRoom;
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
