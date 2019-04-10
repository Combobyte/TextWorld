import java.util.ArrayList;

public class TakeCommand implements Command {

    private Graph graph;
    private String command;
    private Player player;

    public TakeCommand(Graph g, Player p){
        graph = g;
        player = p;
    }
    @Override
    public boolean execute() {
        Graph.Node playerRoom = player.getCurrentRoom();
        Item i = playerRoom.removeItem(command);
        if(i != null){
            player.addItem(i);
            return true;
        }
        return false;
    }

    @Override
    public void init(String val) {
        command = val;
    }
}
