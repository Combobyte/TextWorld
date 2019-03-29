import java.util.ArrayList;

public class Player {

    private ArrayList<Item> inventory;
    String name;

    public Player(String n){
        name = n;
        inventory = new ArrayList<>();
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
}
