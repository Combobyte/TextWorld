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

    public void addNode(String name, String desc){
        nodes.put(name, new Graph.Node(name, desc));
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
        private ArrayList<Creature> creatures;
        private String desc;

        private Node(String s){
            name = s;
            connections = new HashMap<>();
            items = new ArrayList<>();
            creatures = new ArrayList<>();
            this.desc = "no description";
        }

        private Node(String s, String desc){
            name = s;
            this.desc = desc;
            connections = new HashMap<>();
            items = new ArrayList<>();
            creatures = new ArrayList<>();
        }

        private Node(){
            this("no name", "No description");
        }

        public String getDesc(){
            return desc;
        }

        public Graph.Node getRandomRoom(){
            ArrayList<Graph.Node> rooms = new ArrayList<>(connections.values());
            if(rooms.size() == 0){
                return null;
            }
            return rooms.get((int)(Math.random() * rooms.size()));
        }

        public void addCreature(Creature c){
            creatures.add(c);
        }

        public void removeCreature(Creature c){
            creatures.remove(c);
        }

        public ArrayList<Creature> getCreatures(){
            return creatures;
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

        public String getItemNames(){
            String out = "";
            for(Item i : items){
                out+= " " + i.getName() + ": " + i.getDesc();
            }
            out = out.trim();
            return out;
        }

        public String getConnectionsAsString(){
            String out = "";
            for(String key : connections.keySet()){
                out += " " + connections.get(key).getName() + ": " + connections.get(key).getDesc();
            }
            out = out.trim();
            return out;
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

        public ArrayList<Graph.Node> getConnections(){
            ArrayList<Graph.Node> output = new ArrayList<>();
            for(String key : connections.keySet()){
                output.add(connections.get(key));
            }
            return output;
        }

        public String getNumbersOfCreatures(){
            ArrayList<Entry> nums = new ArrayList<>();
            for(Creature c : creatures){
                String animalType = c.getAnimalType();
                boolean inEntry = false;
                for(Entry e : nums){
                    if(e.getName().equals(animalType)){
                        e.add();
                        inEntry = true;
                        break;
                    }
                }
                if(!inEntry){
                    nums.add(new Entry(animalType));
                }
            }
            String output = "";
            for(Entry e : nums){
                output += (e.getNum() + "x " + e.getName() + " ");
            }
            output = output.trim();
            return output;
        }

        public String toString(){
            return name + ": " + desc;
        }
    }
}
