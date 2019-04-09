import java.util.*;

public class DijkstraAlgorithm {

    private final List<Level.Room> rooms;
    private Set<Level.Room> settledRooms;
    private Set<Level.Room> unSettledRooms;
    private Map<Level.Room, Level.Room> predecessors;
    private Map<Level.Room, Integer> distance;

    public DijkstraAlgorithm(Level level) {
        this.rooms = new ArrayList<Level.Room>(level.getNodes());
    }

    public void execute(Level.Room source) {
        settledRooms = new HashSet<Level.Room>();
        unSettledRooms = new HashSet<Level.Room>();
        distance = new HashMap<Level.Room, Integer>();
        predecessors = new HashMap<Level.Room, Level.Room>();

        distance.put(source, 0);
        unSettledRooms.add(source);

        while (unSettledRooms.size() > 0) {
            Level.Room n = getMinimum(unSettledRooms);
            settledRooms.add(n);
            unSettledRooms.remove(n);
            findMinimalDistances(n);
        }
    }

    private void findMinimalDistances(Level.Room room) {
        List<Level.Room> neighbors = new ArrayList<>(room.getNeighbors());
        for (Level.Room target : neighbors) {
            if (getShortestDistance(target) > getShortestDistance(room)) {
                distance.put(target, getShortestDistance(room));
                predecessors.put(target, room);
                unSettledRooms.add(target);
            }
        }
    }


    private Level.Room getMinimum(Set<Level.Room> rooms) {
        Level.Room minimum = null;
        for (Level.Room n : rooms) {
            if (minimum == null) {
                minimum = n;
            } else if (getShortestDistance(n) < getShortestDistance(minimum)) {
                minimum = n;
            }
        }
        return minimum;
    }

    private int getShortestDistance(Level.Room n) {
        Integer d = distance.get(n);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    private boolean isSettled(Level.Room n) {
        return settledRooms.contains(n);
    }
}
