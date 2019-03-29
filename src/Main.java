
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

        Graph.Node current = g.getNode("hall");

        Scanner s = new Scanner(System.in);
        String response = "";

        while (!response.equals("quit")){
            System.out.println("You are currently in the: " + current.getName());
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
                if(current.getNode(reconstructed) == null) System.out.println("That room does not exist");
                else current = current.getNode(reconstructed);
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
                g.addDirectedEdge(current.getName(), reconstructed);
            }else if(response.equals("look")){
                String out = "";
                ArrayList<String> names = current.getConnectionNames();
                for(int i = 0; i < names.size(); i++){
                    out += names.get(i);
                    if(i + 1 != names.size()){
                        out += " ";
                    }
                }
                System.out.println("The rooms that you can go to are: " + out);
            }else{
                System.out.println("The commands you can run are:");
                System.out.println("look: tells you the rooms you can go to");
                System.out.println("go <room>: send you to that room");
                System.out.println("add room <room>: creates a new room attached to the one that you are in");
                System.out.println("quit: ends the game");
            }
            System.out.println();
        }
    }
}
