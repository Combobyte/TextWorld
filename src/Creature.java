public abstract class Creature {

    protected String name;
    protected String description;
    protected Graph.Node room;
    protected Player player;
    protected String animalType;

    public Creature(Graph.Node room, String name, String desc, Player p){
        this.name = name;
        this.room = room;
        this.description = desc;
        this.player = p;
        room.addCreature(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void move(Graph.Node newRoom){
        this.room.removeCreature(this);
        this.room = newRoom;
        this.room.addCreature(this);
    }

    public boolean isWithPlayer(){
        return player.getCurrentRoom().equals(room);
    }

    public abstract void act();

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }
}
