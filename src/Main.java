import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static HashMap<String, Command> commandIds = new HashMap<>();

    public static void main(String[] args) {
        Graph g = new Graph();
        Player p = new Player("guy", "Got destroyed by meta knight in brawl");
        initializeCommandIds(g, p);

        g.addNode("Hub", "The start of the world");
        g.addNode("FRC competition", "Wow! That's a lot of robots! Copyright: Rayan Hirech");
        g.addNode("The graveyard", "This place is quite creepy");
        g.addNode("Grave", "Why did you jump in there!?");
        g.addNode("The airport", "Did you dump out your water?");
        g.addNode("The airplane","Grab a window seat! Quick, or someone else will take it!");
        g.addNode("France", "Baguette");
        g.addNode("Italy", "PIZZA! SPAGHETTI!");
        g.addNode("India", "The home of the horrible T-series");
        g.addNode("Space", "Quite empty");
        g.addNode("Black hole", "Orange donut");
        g.addNode("Mars", "Hi, Elon!");
        g.addNode("Space station", "Its like a hotel, but in space!");
        g.addNode("Rocket", "F = mV + (p - p)A");
        g.addNode("Kennedy space center", "Why does the whiteboard say pi = 3?");
        g.addNode("UC Berkley", "You see a man wearing a stanford rejects t-shirt");
        g.addNode("Stanford", "You must have an iq above 10000 to enter this building");

        g.addUndirectedEdge("Hub", "The graveyard");
        g.addUndirectedEdge("The graveyard", "Grave");
        g.addUndirectedEdge("Hub", "The airport");
        g.addDirectedEdge("The airport", "The airplane");
        g.addDirectedEdge("The airplane", "India");
        g.addDirectedEdge("The airplane", "France");
        g.addDirectedEdge("The airplane", "Italy");
        g.addDirectedEdge("The airplane", "Kennedy space center");
        g.addDirectedEdge("The airplane", "UC Berkley");
        g.addDirectedEdge("The airplane", "Stanford");
        g.addDirectedEdge("The airplane", "FRC competition");
        g.addDirectedEdge("FRC competition", "The airport");
        g.addDirectedEdge( "India", "The airport");
        g.addDirectedEdge( "France", "The airport");
        g.addDirectedEdge("Italy", "The airport");
        g.addDirectedEdge("Kennedy space center", "The airport");
        g.addDirectedEdge( "UC Berkley", "The airport");
        g.addDirectedEdge("Stanford", "The airport");
        g.addUndirectedEdge("Kennedy space center", "Rocket");
        g.addUndirectedEdge("Rocket", "Space station");
        g.addUndirectedEdge("Rocket", "Mars");
        g.addUndirectedEdge("Rocket", "Black hole");
        g.addUndirectedEdge("Rocket", "Space");
        g.addUndirectedEdge("Space station", "Space");

        g.getNode("Hub").addItem(new Item("Box", "Box of doom"));

        ArrayList<Creature> creatures = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            creatures.add(new Chicken(g.getRandomRoom(), "chicken", "cute", p));
            creatures.add(new Popstar(g.getRandomRoom(), "popstar", "not cute", p));
            creatures.add(new Wumpus(g.getRandomRoom(), "wumpus", "run forrest run", p));
        }

        p.setCurrentRoom(g.getNode("Hub"));
        Scanner s = new Scanner(System.in);
        String response = "";
        while (!response.equals("quit")) {
            boolean displaySuccess = true;
            System.out.println("Your current room: " + p.getCurrentRoom().toString());
            System.out.print("> ");
            response = s.nextLine();
            if (response.equals("quit")){
                break;
            }
            Command command = getCommand(response);
            if(command instanceof LookCommand){
                displaySuccess = false;
            }
            boolean success = command.execute();
            if(!success){
                System.out.println("Your action failed!");
                System.out.println("Some of the commands you can use are: ");
                System.out.println("take <item>");
                System.out.println("put <item>");
                System.out.println("go <room>");
                System.out.println("look");
            }else if(displaySuccess){
                System.out.println("Action successful!");
            }
            System.out.println("*******************");
            for(Creature c : creatures){
                c.act();
            }
        }
    }

    private static void initializeCommandIds(Graph graph, Player player){
        commandIds.put("go", new GoCommand(graph, player));
        commandIds.put("look", new LookCommand(graph, player));
        commandIds.put("put", new PutCommand(graph, player));
        commandIds.put("take", new TakeCommand(graph, player));
//        commandIds.put("add", new AddRoomCommand(graph, player));

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
        if(command.indexOf(" ") == -1){
            return command;
        }
        return command.substring(0, command.indexOf(" "));
    }
}
