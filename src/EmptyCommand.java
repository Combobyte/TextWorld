public class EmptyCommand implements Command {

    public EmptyCommand(){

    }
    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public void init(String val) {

    }
}
