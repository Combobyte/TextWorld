public class Item {

    private String name;
    private String desc;

    public Item(String name, String description){
        this.name = name;
        desc = description;
    }

    public Item(){
        name = "nothing";
        desc = "completely nothing";
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
