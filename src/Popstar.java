import java.util.ArrayList;

public class Popstar extends Creature{

    public Popstar(Graph.Node room, String name, String desc, Player p){
        super(room, name, desc, p);
    }

    @Override
    public void act() {
        Graph.Node newRoom = getRoomTowardsPlayer();
        if(newRoom != null) {
            move(newRoom);
        }
    }

    private Graph.Node getRoomTowardsPlayer() {
        ArrayList<Graph.Node> connections = room.getConnections();
        for(Graph.Node node : connections){
            for(Graph.Node connectionNodes : node.getConnections()){
                if(connectionNodes.equals(player.getCurrentRoom())){
                    return connectionNodes;
                }
            }
        }
        return null;
    }
}
