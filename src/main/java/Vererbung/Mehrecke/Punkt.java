package Vererbung.Mehrecke;

public class Punkt extends GeomObj{
    double x,y;
    Punkt(double xv, double yv){
        x = xv;
        y = yv;
    }

    double getX(){
        return x;
    }

    double getY(){
        return y;
    }

    public String toString(){
        return "Punkt (" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Punkt p){
            return this.x == p.x && this.y == p.y;
        }
        return false;
    }
}