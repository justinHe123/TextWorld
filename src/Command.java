public interface Command {
    public void init(Player p, String userString);
    public boolean execute();
}
