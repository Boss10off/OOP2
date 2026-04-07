package Vererbung.Mehrecke;

public class Quadrat extends Rechteck{

    Quadrat(Strecke[] s){
        super(s);
    }

    Quadrat(Strecke s) {
        super(s,s);
    }

    @Override
    public String toString() {
        return "Quadrat: " + this.seiten[0].toString();
    }

}
