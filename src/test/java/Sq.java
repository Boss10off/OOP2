import InterfacesStart.Function;

public class Sq implements Function {
    @Override
    public int execute(int x) {
        return x*x;
    }
}
