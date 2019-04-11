import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Build up graph of connected nodes
        Level g = new Level();
        init(g);

        //"game loop" where I get user input and move the player
        Player p = new Player("Your Boy", "The OG");
        p.setCurrentRoom(g.getRoom("hall"));

        String response = "";
        Scanner in = new Scanner(System.in);
        while(true) {
            Level.Room currentRoom = p.getCurrentRoom();
            printInfo(currentRoom);
            response = in.nextLine();
            Command c = parseCommand(response);
            int spaceIndex = response.indexOf(" ");
            c.init(p, response.substring(spaceIndex + 1)); //no if statemnt needed bc -1 + 1 = 0
            if (c instanceof QuitCommand) break;
            c.execute();
            g.moveAllMobs();

        }


//        do{
//            //display the room and the exits
//            try {
//                Level.Room currentRoom = p.getCurrentRoom();
//                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//                System.out.println("You are currently in the " + currentRoom.getName());
//                System.out.println("What would you like to do?");
//                System.out.println("COMMANDS: go, stay, look, add, link, take, drop, quit");
//
//                response = in.nextLine();
//
//                String[] words = response.split(" ");
//                String firstWord = words[0];
//                String remainingWords = combineWithSpace(words, 1);
//
//                if (firstWord.equals("go")) { //find a way to get all mobs to act
//                    g.moveAllMobs();
//                    if(!p.moveToRoom(combineWithSpace(words, 1))) System.err.println("Something went wrong!");
//                } else if (firstWord.equals("stay")){
//                    g.moveAllMobs();
//                }
//                else if (firstWord.equals("look")) {
//                    System.out.print("You hear: " );
//                    for (Mob mob : currentRoom.getMobs()){
//                        mob.act();
//                    }
//                    System.out.println("\nDESCRIPTION: " + currentRoom.getDescription());
//                    System.out.println("ITEMS: " + currentRoom.displayItems());
//                    System.out.println("YOU CAN GO TO: " + currentRoom.getNeighborNames());
//                }
//                else if (firstWord.equals("add")) currentRoom.addNeighbor(g.addRoom(remainingWords));
//                else if (firstWord.equals("link")) currentRoom.addNeighbor(g.getRoom(remainingWords));
//                else if (firstWord.equals("take")){
//                    p.addItem(currentRoom.removeItem(remainingWords));
//                    System.out.println(remainingWords + " has been taken.");
//                }
//                else if (firstWord.equals("drop")){
//                    currentRoom.addItem(p.removeItem(remainingWords));
//                    System.out.println(remainingWords + " has been dropped.");
//                }
//                else if (firstWord.equals("quit")) continue;
//                else System.out.println("INVALID COMMAND");
//
//            } catch (Exception e){
//                System.err.println("Something went wrong!");
//                e.printStackTrace();
//            }
//        } while (!response.equals("quit"));
    }

    public static void init(Level g) {
        g.addRoom("hall", "You are in a long hallway. You feel the great distance as a void staring deep into your soul.");
        g.addRoom("closet", "You are in a cramped closet. Someone needs to do their laundry, but you don't know who.");
        g.addRoom("bedroom", "You are in a bedroom. There are no beds.");
        g.addRoom("hallway", "You are in a hallway. Like a hall, but a different.");
        g.addRoom("definitely not a room", "How did you get here??");
        g.addRoom("DO NOT ENTER", "DID YOU READ THE SIGN???");
        g.addUndirectedEdge("hall", "closet");
        g.addUndirectedEdge("hall", "bedroom");
        g.addUndirectedEdge("bedroom", "hallway");
        g.addUndirectedEdge("hallway", "definitely not a room");
        g.addUndirectedEdge("hallway", "DO NOT ENTER");
        g.addUndirectedEdge("DO NOT ENTER", "hall");

        g.getRoom("hall").addItem("Key");
        g.getRoom("bedroom").addItem("Vase");
        g.getRoom("definitely not a room").addItem("GOD KILLER");
        g.getRoom("DO NOT ENTER").addItem("Discarded Wrappers");

        g.getRoom("bedroom").addMob(new Chicken(g.getRoom("bedroom")));
    }

    public static String combineWithSpace(String[] words, int start) {
        String s = "";
        for (int i = start; i < words.length; i++) {
            s += words[i] + " ";
        }
        return s.trim();
    }

    public static Command parseCommand(String name) {
        int firstSpace = name.indexOf(" ");

        String commandWord = name.substring(0, firstSpace);

        Class cls = null;
        try {
            cls = Class.forName(commandWord);
            Command obj = (Command) cls.newInstance();

            return obj;
        } catch (Exception e) {
            System.out.println("There was a problem creating a command object for " + name);
            return new BlankCommand();
        }
    }

    public static void printInfo(Level.Room currentRoom){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("You are currently in the " + currentRoom.getName());
        System.out.println("What would you like to do?");
        System.out.println("COMMANDS: go, look, take, drop, quit");
    }

    public static String convertToCommand(String commandWord) {
        char[] arr = commandWord.toCharArray();
        arr[0] = (char) ((int) arr[0] - 32);
        return arr[0] + commandWord.substring(1) + "Command";
    }
}
