import java.util.*;

public class Graph {
    private HashMap<String, Node> nodes;

    public Graph(){
        nodes = new HashMap<>();
    }

    public Node addNode(String name){
        Node n = new Node(name);
        nodes.put(name, n);
        return n;
    }

    public ArrayList<Node> getNodes(){
        return new ArrayList<Node>(nodes.values());
    }

    public Node addNode(String name, String description){
        Node n = new Node(name, description);
        nodes.put(name, n);
        return n;
    }

    public void addDirectedEdge(String name1, String name2){
        Node n1 = getNode(name1);
        Node n2 = getNode(name2);
        n1.addNeighbor(n2);
    }

    public void addUndirectedEdge(String name1, String name2){
        Node n1 = getNode(name1);
        Node n2 = getNode(name2);
        n1.addNeighbor(n2);
        n2.addNeighbor(n1);
    }

    public Node getNode(String name){
        return nodes.get(name);
    }

    public List<Mob> getAllMobs(){
        List<Mob> mobs = new ArrayList<Mob>();
        for (Node n : nodes.values()){
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

    public class Node{
        private String name;
        private HashMap<String, Node> neighbors;
        private String description;
        private List<Item> items;
        private List<Mob> mobs;

        public Node(String name){
            this(name, "placeholder description");
        }

        public Node(String name, String description){
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

        public void addNeighbor(Node n){
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

        public ArrayList<Node> getNeighbors(){
            return new ArrayList<Node>(neighbors.values());
        }

        /**
         * Returns neighbor whose name is name. Returns null otherwise
         * @param name name of neighboring node to return
         * @return returns neighboring node with correct name
         */
        public Node getNeighbor(String name){
            return neighbors.get(name);
        }

        public Node getRandomNeighbor(){
            List<Node> neighborNodes = new ArrayList<>(neighbors.values());
            return neighborNodes.get((int) (Math.random() * neighborNodes.size()));
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
