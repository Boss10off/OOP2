import InterfacesStart.Function;
import InterfacesStart.OnAllElementsOfAMap;

public class TestOnExecuteMap{
    public static void main(String[] args) {
        testMap();
    }

    private static void testMap() {
        //vorbereiten
        int[] p = {1,2,3,4,5,6,7,8,9,10};
        Function p5 = new Plus5();
        //ausführen
        int[] erg = OnAllElementsOfAMap.map(p,p5);
        //prüfen
        assertEquals(erg[0],6);
        assertEquals(erg[1],7);
        assertEquals(erg[2],8);
        //vorbereiten
        int[] p2 = {1,2,3,4,5,6,7,8,9,10};
        Function sq = new Sq();
        //ausführen
        int[] erg2 = OnAllElementsOfAMap.map(p2,sq);
        //prüfen
        assertEquals(erg2[0],1);
        assertEquals(erg2[1],4);
        assertEquals(erg2[2],9);
        assertEquals(erg2[3],16);
    }

    private static void assertEquals(int i, int i1) {
        if(i == i1) System.out.println("passed");
        else System.out.println("failed");
    }

}
