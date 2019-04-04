import java.util.ArrayList;

public class Path {
    private ArrayList<Graph.Node> path;

    public Path(){
        path = new ArrayList<>();
    }

    public Path(Path path){
        this.path = (ArrayList<Graph.Node>)path.getPath().clone();
    }

    public ArrayList<Graph.Node> getPath(){
        return path;
    }

    public void add(Graph.Node n){
        path.add(n);
    }
}
