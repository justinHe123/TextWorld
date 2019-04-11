import java.util.*;

public class Level {
    private HashMap<String, Room> rooms;

    public Level(){
        rooms = new HashMap<>();
    }

    public Room addRoom(String name){
        Room n = new Room(name);
        rooms.put(name, n);
        return n;
    }

    public ArrayList<Room> getRooms(){
        return new ArrayList<Room>(rooms.values());
    }

    public Room addRoom(String name, String description){
        Room n = new Room(name, description);
        rooms.put(name, n);
        return n;
    }

    public void addDirectedEdge(String name1, String name2){
        Room n1 = getRoom(name1);
        Room n2 = getRoom(name2);
        n1.addNeighbor(n2);
    }

    public void addUndirectedEdge(String name1, String name2){
        Room n1 = getRoom(name1);
        Room n2 = getRoom(name2);
        n1.addNeighbor(n2);
        n2.addNeighbor(n1);
    }

    public Room getRoom(String name){
        return rooms.get(name);
    }

    public List<Mob> getAllMobs(){
        List<Mob> mobs = new ArrayList<Mob>();
        for (Room n : rooms.values()){
            for (Mob mob : n.getMobs()){
                mobs.add(mob);
            }
        }
        return mobs;
    }

    public void moveAllMobs(){
        List<Mob> mobs = getAllMobs();
        for (Mob mob : mobs){
            mob.move();;
        }
    }

    public class Room {
        private String name;
        private HashMap<String, Room> neighbors;
        private String description;
        private List<Item> items;
        private List<Mob> mobs;

        public Room(String name){
            this(name, "placeholder description");
        }

        public Room(String name, String description){
            neighbors = new HashMap<>();
            this.name = name;
            this.description = description;
            items = new ArrayList<>();
            mobs = new ArrayList<>();
        }

        public String getDescription(){
            return description;
        }

        public void setDescription(String d){
            description = d;
        }

        public void addNeighbor(Room n){
            neighbors.put(n.getName(), n);
        }

        /**
         * Returns a String of the names of all the neighbors of this node
         * @return
         */
        public String getNeighborNames(){
            String s = "";
            for (String name : neighbors.keySet()){
                s += name + ",";
            }
            if (s.length() == 0) return s;
            return s.substring(0, s.length() - 1);
        }

        public ArrayList<Room> getNeighbors(){
            return new ArrayList<Room>(neighbors.values());
        }

        /**
         * Returns neighbor whose name is name. Returns null otherwise
         * @param name name of neighboring node to return
         * @return returns neighboring node with correct name
         */
        public Room getNeighbor(String name){
            return neighbors.get(name);
        }

        public Room getRandomNeighbor(){
            List<Room> neighborRooms = new ArrayList<>(neighbors.values());
            return neighborRooms.get((int) (Math.random() * neighborRooms.size()));
        }

        public String getName(){
            return this.name;
        }

        public List<Item> getItems(){
            return items;
        }

        public String displayItems(){
            String s = "";
            for (Item i : items){
                s += i.getName() + "; ";
            }
            if (s.length() == 0) return s;
            return s.substring(0, s.length() - 2);
        }

        public void addItem(String name){
            items.add(new Item(name));
        }

        public void addItem(String name, String description){
            items.add(new Item(name, description));
        }

        public void addItem(Item item){
            items.add(item);
        }

        public Item removeItem(String name){
            for (int i = 0; i < items.size(); i++){
                if (items.get(i).getName().equals(name)) return items.remove(i);
            }
            return null;
        }

        public boolean destroyItem(String name){
            for (Item i : items){
                if (i.getName().equals(name)) return items.remove(i);
            }
            return false;
        }

        public List<Mob> getMobs(){
            return mobs;
        }

        public void addMob(Mob mob){
            mobs.add(mob);
        }

        public Mob removeMob(Mob mob){
            return mobs.remove(mobs.indexOf(mob));
        }
    }
}
