import java.util.ArrayList;

public class LookCommand implements Command {

    private Graph graph;
    private Player player;
    private String command;

    public LookCommand(Graph g, Player p){
        graph = g;
        player = p;
    }
    @Override
    public boolean execute() {
        Graph.Node playerRoom = player.getCurrentRoom();
        return true;
    }

    @Override
    public void init(String val) {
        command = val;
    }
}
