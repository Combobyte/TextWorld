
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static HashMap<String, Command> commandIds = new HashMap<>();

    public static void main(String[] args) {

        Graph g = new Graph();

        g.addNode("hall");
        g.addNode("dungeon");
        g.addNode("closet");
        g.addNode("castle");

        g.addDirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");
        g.addDirectedEdge("castle", "hall");

        g.getNode("hall").addItem(new Item("box", "box of doom"));

        Scanner s = new Scanner(System.in);
        String response = "";

        Player p = new Player("guy", "Got destroyed by meta knight in brawl");
        p.setCurrentRoom(g.getNode("hall"));

        ArrayList<Creature> creatures = new ArrayList<>();
        for(int i = 0; i < 30; i++){
            creatures.add(new Chicken(g.getRandomRoom(), "chicken", "Cannot conscript into the army", p));
            creatures.add(new Popstar(g.getRandomRoom(), "wumpus", "A man who was corrupted by using meta knight in brawl", p));
        }

        while (!response.equals("quit")){
            int numCreatures = 0;
            for(Creature c : creatures){
                if(c.isWithPlayer()){
                    numCreatures++;
                }
            }
            boolean moveCreatures = true;
            System.out.println("You are currently in the: " + p.getCurrentRoom().getName());
            System.out.println("There are " + numCreatures + " creatures in the room");
            System.out.print("> ");
            response = s.nextLine();
            if(response.indexOf("go ") == 0){
                String[] arr = response.split(" ");
                String reconstructed = "";
                for(int i = 1; i < arr.length; i++){
                    reconstructed += arr[i];
                    if(i + 1 != arr.length){
                        reconstructed += " ";
                    }
                }
                if(p.getCurrentRoom().getNode(reconstructed) == null) System.out.println("That room does not exist");
                else p.setCurrentRoom(p.getCurrentRoom().getNode(reconstructed));
            }else if (response.equals("quit")){
                System.out.println("Thanks for playing!");
            }else if(response.indexOf("add room ") == 0){
                System.out.println("room added");
                String arr[] = response.split(" ");
                String reconstructed = "";
                for(int i = 2; i < arr.length; i++){
                    reconstructed += arr[i];
                    if(i + 1 != arr.length){
                        reconstructed += " ";
                    }
                }
                g.addNode(reconstructed);
                g.addDirectedEdge(p.getCurrentRoom().getName(), reconstructed);
            }else if(response.equals("look")) {
                System.out.println("The rooms that you can go to are: " + p.getCurrentRoom().getConnectionsAsString());
                System.out.println("The items in the room are: " + p.getCurrentRoom().getItemNames());
                displayNumbersOfAnimals(p);
            }else if(response.indexOf("take ") == 0) {
                String arr[] = response.split(" ");
                String reconstructed = "";
                for (int i = 1; i < arr.length; i++) {
                    reconstructed += arr[i];
                    if (i + 1 != arr.length) {
                        reconstructed += " ";
                    }
                }
                Item i = p.getCurrentRoom().removeItem(reconstructed);
                if (i != null) {
                    p.addItem(i);
                    System.out.println("Item taken!");
                } else {
                    System.out.println("That item does not exist");
                }
                moveCreatures = false;
            }else if(response.indexOf("place ") == 0){
                String arr[] = response.split(" ");
                String reconstructed = "";
                for (int i = 1; i < arr.length; i++) {
                    reconstructed += arr[i];
                    if (i + 1 != arr.length) {
                        reconstructed += " ";
                    }
                }
                Item i = p.removeItem(reconstructed);
                if(i != null){
                    p.getCurrentRoom().addItem(i);
                    System.out.println("Item placed!");
                }else{
                    System.out.println("You do not have that item");
                }
                moveCreatures = false;
            }else{
                System.out.println("The commands you can run are:");
                System.out.println("look: tells you the rooms you can go to");
                System.out.println("go <room>: send you to that room");
                System.out.println("add room <room>: creates a new room attached to the one that you are in");
                System.out.println("take <item>: takes said item from the room");
                System.out.println("place <item>: places said item from your inventory in the room");
                System.out.println("quit: ends the game");
                moveCreatures = false;
            }
            System.out.println("* * * * * * * * * * * *");
            if(moveCreatures) {
                for (Creature c : creatures) {
                    c.act();
                }
            }
        }
    }

    private static void displayNumbersOfAnimals(Player p) {
        Graph.Node currRoom = p.getCurrentRoom();
        ArrayList<Creature> animals = currRoom.getCreatures();
        int numChickens = 0;
        int numPopstars = 0;
        int numWumpus = 0;
        for(Creature c : animals){
            if(c instanceof Chicken){
                numChickens++;
            }else if (c instanceof Popstar){
                numPopstars++;
            }
        }
        System.out.println("The number of chickens in the area is " + numChickens);
        System.out.println("The number of pop stars in the area is " + numPopstars);
        System.out.println("The number of wumpuses in the area is " + numWumpus);
    }

    public static Command getCommand(String command){
        String id = getFirstWord(command);
        String value = getLastWord(command);
        Command c = commandIds.get(id);
        if(c == null){
            c = new EmptyCommand();
        }
        c.init(value);
        return c;
    }

    private static String getLastWord(String command) {
        return command.substring(command.indexOf(" ") + 1);
    }

    private static String getFirstWord(String command) {
        return command.substring(0, command.indexOf(" "));
    }
}
