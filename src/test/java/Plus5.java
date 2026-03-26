import InterfacesStart.Function;

public class Plus5 implements Function {
    @Override
    public int execute(int x) {
        return x+5;
    }
}
