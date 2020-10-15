import java.util.Arrays;
import java.util.function.Consumer;

public class Ex1 {
    public static void main(String[] args) {
        /*
        * Programación funcional
        * */
        Integer[] ar = new Integer[]{1,2,3,4,5,6,7};
        Print print = new Print();

        /*
        * Maneras de imprimir primitivas
        * */

        //System.out.println(Arrays.toString(ar));

        /*for (int i = 0; i < ar.length; i++) {
            System.out.println(ar[i]);
        }*/

        /*
        * Programacion funcional
        *
        * Stream -> Tipo de array
        * stream().count() -> devuelve un long
        * */

        //count() devuelve un long con el número de elementos de la array
        long numEl = Arrays.stream(ar).count();

        //forEach() -> necesita de un objeto para ejecutar sus métodos
        Arrays.stream(ar).forEach(print);

    }


    static class Print implements Consumer<Integer>{
        // Método accept que devolverá los elementos
        @Override
        public void accept(Integer integer) {
            System.out.println(integer);
        }
    }

}
