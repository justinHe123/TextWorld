import java.util.*;

public class Player {
    private String name, description;
    private List<Item> items;
    private Level.Room currentRoom;

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

    public Level.Room getCurrentRoom(){
        return currentRoom;
    }

    public void setCurrentRoom(Level.Room newRoom){
        currentRoom = newRoom;
    }

    public boolean moveToRoom(String name){
        Level.Room room = currentRoom.getNeighbor(name);
        return moveToRoom(room);
    }

    public boolean moveToRoom(Level.Room room){
        if (room != null) {
            setCurrentRoom(room);
            return true;
        }
        return false;
    }
}
