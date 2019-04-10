import java.util.ArrayList;

public class Wumpus extends Creature{

    public Wumpus(Graph.Node room, String name, String desc, Player p){
        super(room, name, desc, p);
        setAnimalType("wumpus");
    }

    @Override
    public void act() {
        Graph.Node newRoom = runAway();
        if (newRoom != null){
            move(newRoom);
        }
    }

    public Graph.Node runAway(){
        ArrayList<Graph.Node> connections = room.getConnections();
        for(Graph.Node node : connections){
            for(Graph.Node connectionNodes : node.getConnections()){
                if(connectionNodes.equals(player.getCurrentRoom())){
                    Graph.Node possibleMove = room.getRandomRoom();
                    if(possibleMove == null) return null;
                    while((possibleMove.equals(node))){
                        possibleMove = room.getRandomRoom();
                    }
                    return possibleMove;
                }
            }
        }
        return null;
    }
}
