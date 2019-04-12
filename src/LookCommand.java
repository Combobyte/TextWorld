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
        System.out.println("The rooms you can go to are: " + playerRoom.getConnectionsAsString());
        System.out.println("The items in the room are: " + playerRoom.getItemNames());
        System.out.println("The creatures in the room are: " + playerRoom.getNumbersOfCreatures());
        return true;
    }

    @Override
    public void init(String val) {
        command = val;
    }
}
