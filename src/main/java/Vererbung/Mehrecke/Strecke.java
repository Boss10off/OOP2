package Vererbung.Mehrecke;

public class Strecke extends GeomObj{
    Punkt start,ende;
    Strecke (Punkt s, Punkt e){
        start = s;
        ende = e;
    }
    double length(){
        double x = start.getX() - ende.getX();
        double y = start.getY() - ende.getY();
        return Math.sqrt(x*x + y*y);
    }

    @Override
    public String toString() {
        return start.toString() + "-------" + ende.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Strecke st){
            return start.equals(st.start) && ende.equals(st.ende);
        }
        return false;
    }
}
