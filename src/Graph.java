import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Graph {

    private Map<String, Node> nodes;

    public Graph(){
        nodes = new HashMap() {
        };
    }

    public void addNode(String name) {
        nodes.put(name, new Graph.Node(name));
    }

    public Graph.Node getNode(String name) {
        for(String s : nodes.keySet()){
            if(nodes.get(s).getName() == name) return nodes.get(s);
        }
        return null;
    }

    public Graph.Node getRandomRoom(){
        ArrayList<Graph.Node> rooms = new ArrayList<>(nodes.values());
        return rooms.get((int)(Math.random() * rooms.size()));
    }

    public void addUndirectedEdge(String name, String connection) {
        Graph.Node firstNode = getNode(name);
        Graph.Node connectionNode = getNode(connection);
        if(firstNode == null || connectionNode == null){
            System.out.println("Those nodes do not exist");
            return;
        }
        firstNode.addConnection(connectionNode);
        connectionNode.addConnection(firstNode);
    }

    public void addDirectedEdge(String name, String connection) {
        Graph.Node firstNode = getNode(name);
        Graph.Node connectionNode = getNode(connection);
        if(firstNode == null || connectionNode == null){
            System.out.println("Those nodes do not exist");
            return;
        }
        firstNode.addConnection(connectionNode);
    }

    public class Node {

        private String name;
        HashMap<String, Node> connections;
        private ArrayList<Item> items;

        private Node(String s){
            name = s;
            connections = new HashMap<>();
            items = new ArrayList<>();
        }

        private Node(){
            name = "";
            connections = new HashMap<>();
        }

        public Graph.Node getRandomRoom(){
            ArrayList<Graph.Node> rooms = new ArrayList<>(connections.values());
            if(rooms.size() == 0){
                return null;
            }
            return rooms.get((int)(Math.random() * rooms.size()));
        }

        private void addConnection(Node n){
            connections.put(n.getName(), n);
        }

        public void addItem(Item i){
            items.add(i);
        }

        public ArrayList<Item> getItems(){
            return items;
        }

        public Item removeItem(String name){
            Item i = new Item();
            boolean found = false;
            for(Item item : items){
                if(item.getName().equals(name)){
                    i = item;
                    found = true;
                }
            }
            if(!found){
                return null;
            }
            items.remove(i);
            return i;
        }

        public void displayItems(){
            String out = "";
            for(Item i : items){
                out+= " " + i.getName();
            }
            out = out.trim();
            System.out.println(out);
        }

        public void displayConnections(){
            String out = "";
            for(String key : connections.keySet()){
                out += " " + connections.get(key).getName();
            }
            out = out.trim();
            System.out.println(out);
        }

        public String getName(){
            return name;
        }

        public ArrayList<String> getConnectionNames(){
            ArrayList<String> out = new ArrayList<>();

            for(String s : connections.keySet()){
                out.add(connections.get(s).getName());
            }

            return out;
        }

        public Node getNode(String name){
            Node n = new Node();
            boolean found = false;
            for(String s : connections.keySet()){
                if(connections.get(s).getName().equals(name)){
                    n = connections.get(s);
                    found = true;
                    break;
                }
            }
            if(!found){
                return null;
            }
            return n;
        }
    }

}
