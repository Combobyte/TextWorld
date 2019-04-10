public class Entry {

    private String name;
    private int num;

    public Entry(String creatureName){
        name = creatureName;
        num = 0;
    }

    public String getName() {
        return name;
    }

    public int getNum(){
        return num;
    }

    public void add(){
        num++;
    }
}
