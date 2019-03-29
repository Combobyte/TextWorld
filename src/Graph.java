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

        private Node(String s){
            name = s;
            connections = new HashMap<>();
        }

        private Node(){
            name = "";
            connections = new HashMap<>();
        }

        private void addConnection(Node n){
            connections.put(n.getName(), n);
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
