package InterfacesStart;

public class OnAllElementsOfAMap{

        public static int[] map(int[] a, Function f) {
                //! int[] res = new int[a.length];
                for(int i=0; i<a.length; i++){
                        a[i] = f.execute(a[i]);//res[i] = f.execute(a[i]);
                }
                return a;//! res;
        }
}


