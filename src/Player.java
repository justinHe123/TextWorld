import java.util.*;

public class Player {
    private String name, description;
    private List<Item> items;
    private Graph.Node currentRoom;

    public Player (String name, String description){
        this.name = name;
        this.description = description;
        items = new ArrayList<>();
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

    public List<Item> getItems(){
        return items;
    }

    public String displayInventory(){
        String s = "";
        for (Item i : items){
            s += i.getName() + "; ";
        }
        if (s.length() == 0) return s;
        return s.substring(0, s.length() - 2);
    }

    public Graph.Node getCurrentRoom(){
        return currentRoom;
    }

    public void setCurrentRoom(Graph.Node newRoom){
        currentRoom = newRoom;
    }

    public boolean moveToRoom(String name){
        Graph.Node node = currentRoom.getNeighbor(name);
        return moveToRoom(node);
    }

    public boolean moveToRoom(Graph.Node node){
        if (node != null) {
            setCurrentRoom(node);
            return true;
        }
        return false;
    }
}
