import java.util.ArrayList;

public class Path {
    private ArrayList<Level.Room> path;

    public Path(){
        path = new ArrayList<>();
    }

    public Path(Path path){
        this.path = (ArrayList<Level.Room>)path.getPath().clone();
    }

    public ArrayList<Level.Room> getPath(){
        return path;
    }

    public void add(Level.Room n){
        path.add(n);
    }
}
