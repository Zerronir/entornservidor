import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        *
        * forEach() -> necesita de un objeto para ejecutar sus métodos
        * MANERAS DE HACER EL FOREACH
        *
        * 1. Arrays.stream(ar).forEach(print); Que necesita el siguiente objeto -> Print print = new Print();
        * 2. Arrays.stream(ar).forEach((i) -> System.out.println(i));
        * 3. Consumer<Integer> print = (i) -> System.out.println(i);
        * 4. directo Arrays.stream(ar).forEach((i) -> System.out.println(i));
        * */


        // FILTER
        /*
        * Filter nos permite filtrar el resultado y usaremos el método test que nos devolverá un boolean
        *
        * 1. Arrays.stream(ar).filter(mjt).forEach((i)-> System.out.println(i)); --> Pintar todos los elementos mayores que 3
        * 2. Arrays.stream(ar).filter(mjt).forEach((i)-> llista2.add(i)); --> Los metemos en una lista
        * 3. List<Integer> llista3 = Arrays
                                .stream(ar)
                                .filter(mjt)
                                .collect(Collectors.toList()); --> Manera de ordenar los métodos
        *
        * 4. Arrays.stream(ar).filter(mjt).filter(mqs).collect(Collectors.toList()) --> Varios filtros en una misma lista
        * 5. Predicate<Integer> m1 = (i -> i > 3); --> Nos permite simplificar para no crear una clase
        *
        *
        * */

        MajorQueTres mjt = new MajorQueTres();
        List<Integer> llista2 = new ArrayList<>();

        // Como se pinta todo de forma elegante
        List<Integer> llista3 = Arrays
                                .stream(ar)
                                .filter(mjt)
                                .collect(Collectors.toList());

        Arrays.stream(ar).filter(mjt).forEach((i)-> llista2.add(i));
        //System.out.println(llista2);


        MenorQueSis mqs = new MenorQueSis();
        List<Integer> llista4 =
                Arrays.stream(ar)
                        .filter(mjt)
                        .filter(mqs)
                        .collect(Collectors.toList());

        // Podemos sustituir las clases por esto:
        Predicate<Integer> m1 = (i -> i > 3);
        Predicate<Integer> m2 = (i -> i < 6);

        List<Integer> total =
                Arrays.stream(ar)
                        .filter(m1)
                        .filter(m2)
                        .collect(Collectors.toList());

        //System.out.println(total);

        /*
        * FUNCTION
        * Tenemos que crear el elemento con un método apply o usar el elemento Function directamente
        *
        * Half h = new Half();
        * Function<Integer, Double> hl = (i) -> (double) i/2;
        *
        * Arrays.stream(ar).map(h).forEach((i) -> System.out.println(i)); --> Pintamos el resultado
        * Arrays.stream(ar).map(hl).forEach((i) -> System.out.println(i)); --> Pintamos el resultado
        *
        * Arrays.stream(ar).map((i) -> (double) i/2).forEach((i) -> System.out.println(i)); --> Incluimos el function dentro del map
        *
        * */
        Half h = new Half();
        Function<Integer, Double> hl = (i) -> (double) i/2;

        Arrays.stream(ar).map(hl).forEach((i) -> System.out.println(i));

    }

    // Clase para pintar los elementos de la array en programación funcional
    static class Print implements Consumer<Integer>{
        // Método accept que devolverá los elementos
        @Override
        public void accept(Integer integer) {
            System.out.println(integer);
        }
    }

    static class MajorQueTres implements Predicate<Integer>{

        @Override
        public boolean test(Integer integer) {
            return integer > 3;
        }
    }

    static class MenorQueSis implements Predicate<Integer>{

        @Override
        public boolean test(Integer integer) {
            return integer < 6;
        }
    }

    static class Half implements Function<Integer, Double>{

        @Override
        public Double apply(Integer integer) {
            return (double) integer/2;
        }
    }

}
