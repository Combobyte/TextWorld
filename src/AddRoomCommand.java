public class AddRoomCommand implements Command {

    private Graph graph;
    private Player player;
    String value;

    public AddRoomCommand(Graph g, Player p){
        graph = g;
        player = p;
    }
    @Override
    public boolean execute() {
        graph.addNode(value);
        graph.addDirectedEdge(player.getCurrentRoom().getName(), value);
        return true;
    }

    @Override
    public void init(String val) {
        value = val;
    }
}
