
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static HashMap<String, Command> commandIds = new HashMap<>();

    public static void main(String[] args) {
        initializeCommandIds();
        Graph g = new Graph();

        g.addNode("hall");
        g.addNode("dungeon");
        g.addNode("closet");
        g.addNode("castle");

        g.addDirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");
        g.addDirectedEdge("castle", "hall");

        g.getNode("hall").addItem(new Item("box", "box of doom"));

        Player p = new Player("guy", "Got destroyed by meta knight in brawl");
        p.setCurrentRoom(g.getNode("hall"));

        Scanner s = new Scanner(System.in);
        String response = "";
        while (!response.equals("quit")) {
            System.out.print("> ");
            response = s.nextLine();
            Command command = getCommand(response);
            command.execute();
        }
    }

    private static void initializeCommandIds(){

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
