package lamdba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 
 * @see java.util.function.Predicate
 * @see java.util.Optional
 * @author atpt34
 *
 */
public class LambdaExample {

    @FunctionalInterface
    interface Comp {
        int comp(int a);
//        void cp(int a, int b); // makes Comp not functional interface !
    }
    
    @FunctionalInterface
    interface CompGeneric<T> {
        T comp(T a);
    }
    
    public static void main(String[] args) {        
        
        // lambda interface
        Comp incr = (int i) -> {return ++i;};
        Comp incr2 = i -> ++i;
        Comp sqr = (int i) -> i * i;
        CompGeneric<Integer> cb = (i) -> i * i * i;
        CompGeneric<String> ssqr = str -> str + str;
        
        System.out.println(incr.comp(1));
        System.out.println(incr2.comp(-1));
        System.out.println(sqr.comp(12));
        System.out.println(cb.comp(3));
        System.out.println(ssqr.comp("terror"));
        
        int n = 10;
        List<Integer> list = new ArrayList<>(Collections.nCopies(n, 2));
        long t = list.stream().count();
        System.out.println(t);
        list.stream().map(i -> i * i).forEach((i) -> {System.out.println(++i);});
        int sum = list.parallelStream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
//        list.stream().forEach(i -> i = 3); // can't change immutable Integer
//        list.stream().forEach(List::set);
        ArrayList<Integer> list2 = list.stream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println(list2);
        sum = list.stream().reduce(0, Integer::sum);
        System.out.println(sum);
        int product = list.stream().reduce(1, (a, b) -> a * b);
        System.out.println(product);
        int max = IntStream.of(19, 29, 84, 14, -4, 14, 93, 18).reduce(0, Integer::max);
        System.out.println(max);
        max = IntStream.of(19, 29, 84, 14, -4, 14, 93, 18, -34, 15).reduce((a, b) -> a > b ? a : b).getAsInt();
        System.out.println(max);
        
        // infinite generator
        Stream.generate(new Supplier<Integer>() {
            private int n = 0;
            @Override
            public Integer get() {
                return n++;
            }
        }).limit(10) // makes it finite !!!
        .forEach(System.out::println); 
        
        IntStream.range(1, 4)
        .forEach(System.out::println);
        
        Stream.of("d2", "a2", "b1", "b3", "c")
        .filter(s -> {
            System.out.println("filter: " + s);
            return true;
        })
        .forEach(s -> System.out.println("forEach: " + s));
        
        
        Pattern.compile(", ").splitAsStream("a, b, c").forEach(System.out::println);
        
        List<Integer> collect = Stream.iterate(10, nn -> nn + 10).limit(5).collect(Collectors.toList());
        System.out.println(collect);
        
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
        .parallelStream()
        .filter(s -> {
            System.out.format("filter: %s [%s]\n",
                s, Thread.currentThread().getName());
            return true;
        })
        .map(s -> {
            System.out.format("map: %s [%s]\n",
                s, Thread.currentThread().getName());
            return s.toUpperCase();
        })
        .sorted((s1, s2) -> {
            System.out.format("sort: %s <> %s [%s]\n",
                s1, s2, Thread.currentThread().getName());
            return s1.compareTo(s2);
        })
        .forEach(s -> System.out.format("forEach: %s [%s]\n",
            s, Thread.currentThread().getName()));
    }

}
