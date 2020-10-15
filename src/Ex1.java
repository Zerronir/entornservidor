import java.util.Arrays;
import java.util.function.Consumer;

public class Ex1 {
    public static void main(String[] args) {
        /*
        * Programación funcional
        * */
        Integer[] ar = new Integer[]{1,2,3,4,5,6,7};

        /*
        * Maneras de imprimir primitivas
        *
        * System.out.println(Arrays.toString(ar));
        *
        * for (int i = 0; i < ar.length; i++) {
            System.out.println(ar[i]);
          }
        *
        * */

        /*
        * Programacion funcional
        *
        * Stream -> Tipo de array
        * stream().count() -> devuelve un long
        *
        * //count() devuelve un long con el número de elementos de la array
        * long numEl = Arrays.stream(ar).count();
        *
        * */

        /*
        * forEach() -> necesita de un objeto para ejecutar sus métodos
        * MANERAS DE HACER EL FOREACH
        *
        * 1. Arrays.stream(ar).forEach(print); Que necesita el siguiente objeto -> Print print = new Print();
        * 2. Arrays.stream(ar).forEach((i) -> System.out.println(i));
        * 3. Consumer<Integer> print = (i) -> System.out.println(i);
        *
        * */

        Arrays.stream(ar).forEach((i) -> System.out.println(i));


    }


    static class Print implements Consumer<Integer>{
        // Método accept que devolverá los elementos
        @Override
        public void accept(Integer integer) {
            System.out.println(integer);
        }
    }

}
