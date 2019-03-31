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

    public class Node{
        private String name;
        private HashMap<String, Node> neighbors;
        private String description;
        private List<Item> items;

        public Node(String name){
            neighbors = new HashMap<>();
            this.name = name;
            description = "placeholder description";
            items = new ArrayList<>();
        }

        public Node(String name, String description){
            neighbors = new HashMap<>();
            this.name = name;
            this.description = description;
            items = new ArrayList<>();
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

        /**
         * Returns neighbor whose name is name. Returns null otherwise
         * @param name name of neighboring node to return
         * @return returns neighboring node with correct name
         */
        public Node getNeighbor(String name){
            return neighbors.get(name);
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
    }
}
