import java.util.ArrayList;

public class Player {

    private ArrayList<Item> inventory;
    private String name;
    private String desc;
    private Graph.Node currentRoom;

    public Player(String n, String desc){
        name = n;
        inventory = new ArrayList<>();
        this.desc = desc;
    }

    public String getName(){
        return name;
    }

    public void addItem(Item item){
        inventory.add(item);
    }

    public Item removeItem(String name){
        Item i = new Item();
        boolean found = false;
        for(Item item : inventory){
            if(item.getName().equals(name)){
                i = item;
                found = true;
            }
        }
        if(!found){
            return null;
        }
        inventory.remove(i);
        return i;
    }

    public Graph.Node getCurrentRoom(){
        return currentRoom;
    }

    public void setCurrentRoom(Graph.Node room){
        currentRoom = room;
    }
}
