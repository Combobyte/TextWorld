public abstract class Creature {

    protected String name;
    protected Graph.Node room;
    protected String description;
    protected Player player;

    public Creature(Graph.Node room, String name, String desc, Player p){
        this.name = name;
        this.room = room;
        this.description = desc;
        this.player = p;
    }

    public void move(Graph.Node room){
        this.room = room;
    }

    public boolean isWithPlayer(){
        return player.getCurrentRoom().equals(room);
    }

    public abstract void act();
}
