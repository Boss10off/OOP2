package Vererbung.Mehrecke;

public abstract class Mehreck extends GeomObj{
    Strecke[] seiten;
    Mehreck(Strecke[] s){
        seiten = s;
    }
    double umfang(){
        double sum = 0;
        for(Strecke s: seiten){
            sum += s.length();
        }
        return sum;
    }

    abstract double flaeche();
}

