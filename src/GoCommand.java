public class GoCommand implements Command {
    Player p;
    String destination;

    public GoCommand(){
    }

    @Override
    public void init(Player p, String userString) {
        this.p = p;
        this.destination = combineWithSpace(userString, 1);
    }

    @Override
    public boolean execute() {
        return p.moveToRoom(destination);
    }

    private static String combineWithSpace(String userString, int start){
        String[] words = userString.split(" ");
        String s = "";
        for (int i = start; i < words.length; i++){
            s += words[i] + " ";
        }
        return s.trim();
    }
}
