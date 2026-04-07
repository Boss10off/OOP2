package Vererbung.Voegel;

public class Pinguin extends Vogel {
    void schwimmen(){
        if (x >3){
            fliegen();
        }
    }
    Pinguin(){
        super(5);
    }

    @Override
    public void fliegen() {}


}
