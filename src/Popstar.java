public class Popstar extends Creature{

    public Popstar(Graph.Node room, String name, String desc, Player p){
        super(room, name, desc, p);
    }

    @Override
    public void act() {
        boolean playerIsNear = false;
        Graph.Node pRoom = player.getCurrentRoom();
        for(Graph.Node neighbor : room.getConnections()){
            if(neighbor.equals(pRoom)){
                playerIsNear = true;
            }
        }
        if (room.equals(pRoom)) {
            playerIsNear = true;
        }
        if(playerIsNear){
            boolean move = true;
            Graph.Node posMove = room.getRandomRoom();
            if (posMove == null){
                return;
            }
            int numTrys = 0;
            while (!(posMove.equals(pRoom))){
                posMove = room.getRandomRoom();
                numTrys++;
                if(numTrys > 12){
                    move = false;
                    break;
                }
            }
            if(move){
                move(posMove);
            }
        }else{
            Graph.Node posMove = room.getRandomRoom();
            if(posMove != null){
                move(posMove);
            }
        }
    }
}
