import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static HashMap<String, Command> commandIds = new HashMap<>();

    public static void main(String[] args) {
        Graph g = new Graph();
        Player p = new Player("guy", "Got destroyed by meta knight in brawl");
        initializeCommandIds(g, p);
        
        Scanner s = new Scanner(System.in);
        String response = "";
        while (!response.equals("quit")) {
            System.out.print("> ");
            response = s.nextLine();
            Command command = getCommand(response);
            boolean success = command.execute();
            if(!success){
                System.out.println("Your action failed!");
                System.out.println("Some of the commands you can use are: ");
                System.out.println("take <item>");
                System.out.println("put <item>");
                System.out.println("go <room>");
                System.out.println("look");
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
        return command.substring(0, command.indexOf(" "));
    }
}
