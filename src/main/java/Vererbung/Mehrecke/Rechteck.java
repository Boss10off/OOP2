package Vererbung.Mehrecke;

public class Rechteck extends Mehreck{

    Rechteck(Strecke a,Strecke b){
        super(new Strecke[]{a, b});
    }

    public Rechteck(Strecke[] s) {
        super(s);
    }

    @Override
    double flaeche() {
        return seiten[0].length()*seiten[1].length();
    }
}
