public class Chicken extends Creature{

    public Chicken(Graph.Node room, String name, String desc, Player p){
        super(room, name, desc, p);
    }

    @Override
    public void act() {
        Graph.Node goTo = room.getRandomRoom();
        if(goTo != null){
            move(goTo);
        }
    }
}
