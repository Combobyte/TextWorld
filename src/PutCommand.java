public class PutCommand implements Command {

    private Graph graph;
    private Player player;
    private String command;

    public PutCommand(Graph g, Player p){
        graph = g;
        player = p;
    }
    @Override
    public boolean execute() {
        Graph.Node playerRoom = player.getCurrentRoom();
        Item i = player.removeItem(command);
        if(i != null){
            playerRoom.addItem(i);
            return true;
        }
        return false;
    }

    @Override
    public void init(String val) {
        command = val;
    }
}
