
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

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

        Player p = new Player("guy", "just a dude");
        p.setCurrentRoom(g.getNode("hall"));

        while (!response.equals("quit")){
            System.out.println("You are currently in the: " + p.getCurrentRoom().getName());
            System.out.println("What do you want to do?");
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
                System.out.println("The rooms that you can go to are: ");
                p.getCurrentRoom().displayConnections();
                System.out.println("The items in the room are: ");
                p.getCurrentRoom().displayItems();
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
            }else{
                System.out.println("The commands you can run are:");
                System.out.println("look: tells you the rooms you can go to");
                System.out.println("go <room>: send you to that room");
                System.out.println("add room <room>: creates a new room attached to the one that you are in");
                System.out.println("take <item>: takes said item from the room");
                System.out.println("place <item>: places said item from your inventory in the room");
                System.out.println("quit: ends the game");
            }
            System.out.println();
        }
    }
}
