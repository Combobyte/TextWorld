public class GoCommand implements Command {

    private Graph graph;
    private Player player;
    private String command;

    public GoCommand(Graph g, Player p){
        graph = g;
        player = p;
    }
    @Override
    public boolean execute() {
        Graph.Node playerRoom =  player.getCurrentRoom();
        Graph.Node newRoom = playerRoom.getNode(command);
        if(newRoom != null){
            player.setCurrentRoom(newRoom);
            return true;
        }
        return false;
    }

    @Override
    public void init(String val) {
        command = val;
    }
}
