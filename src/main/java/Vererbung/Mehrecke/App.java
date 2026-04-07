package Vererbung.Mehrecke;

public class App {
    public static void main(String[] args) {
        Strecke[] strecke = new Strecke[4];
        strecke[0] = new Strecke(new Punkt(0,0),new Punkt(0,1));
        strecke[1] = new Strecke(new Punkt(0,1),new Punkt(1,1));
        strecke[2] = new Strecke(new Punkt(1,1),new Punkt(1,0));
        strecke[3] = new Strecke(new Punkt(1,0),new Punkt(0,0));
        Quadrat quadrat = new Quadrat(strecke);
        System.out.println(quadrat.toString());
        System.out.println("Quadrat: Umfang: " + quadrat.umfang() + " Fläche: " + quadrat.flaeche());

        Punkt p1 = new Punkt(0,0);
        Punkt p2 = new Punkt(0,0);
        System.out.println(p2.equals(p1));
    }
}
