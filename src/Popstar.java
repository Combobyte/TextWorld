import java.util.ArrayList;

public class Popstar extends Creature{

    public Popstar(Graph.Node room, String name, String desc, Player p){
        super(room, name, desc, p);
        setAnimalType("popstar");
    }

    @Override
    public void act() {
        Graph.Node newRoom = getRoomTowardsPlayer();
        if(newRoom != null) {
            move(newRoom);
        }
    }

    private Graph.Node getRoomTowardsPlayer() {
        if(room.equals(player.getCurrentRoom())){
            return null;
        }
        ArrayList<Graph.Node> connections = room.getConnections();
        for(Graph.Node node : connections){
            if(node.equals(player.getCurrentRoom())){
                return node;
            }
            for(Graph.Node connectionNodes : node.getConnections()){
                if(connectionNodes.equals(player.getCurrentRoom())){
                    return node;
                }
            }
        }
        return null;
    }
}
