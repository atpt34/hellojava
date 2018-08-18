package test_project;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterator.OfInt;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;


public class ThreadLocalExample {

    private static ThreadLocal<Integer> n = new ThreadLocal<>();
    
    static {
        System.out.println(n);
        n.set(10);
        System.out.println(n);
        Stream.of(10, 20, 30, 40, 15).takeWhile(x -> x < 25).forEach(System.out::println);
//        Stream.of(10, 20, 30, 50, 10).dropWhile(x -> x < 25).forEach(System.out::println);
        
//        List<List<String>> a = List.of(List.of("b"), List.of("a", "c"), List.of(), List.of("d"));
//        Stream<String> map = a.stream().flatMap(List::stream);
//        Stream<String> filter = map.filter(s -> s.startsWith("a"));
//        System.out.println(map);
//        System.out.println(filter);
//        map.forEach(System.out::println);
        
        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
//        OfInt spliterator = IntStream.range(0, Integer.MAX_VALUE).spliterator();
        Spliterator<Integer> spliterator = List.of(1, 2, 3, 4, 5).spliterator();
        Spliterator<Integer> trySplit = spliterator.trySplit();
        System.out.println(Integer.toHexString(spliterator.characteristics()));
        long exactSizeIfKnown = spliterator.getExactSizeIfKnown();
        System.out.println(exactSizeIfKnown);
        System.out.println(spliterator.estimateSize());
        System.out.println(trySplit.estimateSize());
        System.out.println(trySplit.getExactSizeIfKnown());
        
        System.out.println("sum = " + (trySplit.getExactSizeIfKnown() + spliterator.getExactSizeIfKnown()));
        
//        Map<Boolean, List<Integer>> collect = Stream.of(3, 16, 5, 4, 8, 2, 1, -5, 12, 5, 7).collect(Collectors.partitioningBy(x -> x % 2 == 0));
//        List<Integer> collect = Stream.of(3, 16, 5, 4, 8, 2, 1, -5, 12, 5, 7).collect(Collectors.mapping(x -> x * 3, Collectors.toList()));
        String collect = Stream.of("a", "b", "c").collect(Collectors.joining(",", "[", "]"));
        System.out.println(collect);
    }
    
    static int[] threadLocalRandomExample() {
        return ThreadLocalRandom.current().ints().limit(n.get()).toArray();
    }
    
    @Test
    public void testThreadLocal() throws Exception {
//        assertArrayEquals(ThreadLocalRandom.current().ints(n.get()).toArray(),
//                threadLocalRandomExample());
    }
    
    class Pair<U, V> {
        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
        public U first;
        public V second;
        @Override
        public String toString() {
            return first + ", " + second;
        }
    }
    
    @Test
    public void testTakeWhile() throws Exception {
        List<Integer> arr = List.of(10, 20, 30, 40, 15);
        
        List<Integer> expected = arr.stream().takeWhile(x -> x < 25).collect(Collectors.toList());
        AtomicInteger i = new AtomicInteger(0);
                        arr.stream()
                         .map(v -> new Pair<Integer, Integer>(v, i.incrementAndGet()))
                         .peek(p -> System.out.println(p))
                         .filter(p -> p.first >= 25)
                         .findFirst();
//                         .map(p -> p.first)
        List<Integer> actual = arr.stream().limit(i.get() - 1).collect(Collectors.toList());
        
        
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testTakeWhile2() throws Exception {
        List<Integer> arr = List.of(10, 20, 30, 40, 15);
        
        List<Integer> expected = arr.stream().takeWhile(x -> x < 25).collect(Collectors.toList());
        
        List<Integer> actual = new ArrayList<>(); 
        Spliterator<Integer> spliterator = arr.spliterator();
            arr.stream()
             .filter(x -> x < 25)
             .peek(v -> spliterator.tryAdvance(actual::add))
             .findFirst();
            spliterator.tryAdvance(actual::add);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testTakeWhile3() throws Exception {
        List<Integer> arr = List.of(10, 20, 30, 40, 15);
        
        List<Integer> expected = arr.stream().takeWhile(x -> x < 25).collect(Collectors.toList());
        
        List<Integer> actual = new ArrayList<>(); 
        arr.stream()
         .peek(v -> actual.add(v))
         .peek(v -> System.out.println(v))
         .filter(x -> x >= 25)
         .findFirst();
        actual.remove(actual.size() - 1);
                     
        assertEquals(expected, actual);
    }
    
    @Test
    public void testDropWhile() throws Exception {
        List<Integer> arr = List.of(10, 20, 30, 40, 15);
        
        List<Integer> expected = arr.stream().dropWhile(x -> x < 25).collect(Collectors.toList());
        AtomicInteger i = new AtomicInteger(0);
                        arr.stream()
                         .peek(v -> i.incrementAndGet())
                         .filter(v -> v >= 25)
                         .findFirst();
        List<Integer> actual = arr.stream().skip(i.get() - 1).collect(Collectors.toList());
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testDropWhile2() throws Exception {
        List<Integer> arr = List.of(10, 20, 30, 40, 15);
        
        List<Integer> expected = arr.stream().dropWhile(x -> x < 25).collect(Collectors.toList());
        Spliterator<Integer> spliterator = arr.spliterator();
                        arr.stream()
                         .filter(x -> x < 25)
                         .peek(v -> spliterator.tryAdvance(p -> {}))
                         .findFirst();
        spliterator.tryAdvance(p -> {});
        List<Integer> actual = new ArrayList<>(); 
        spliterator.forEachRemaining(actual::add);
        
        assertEquals(expected, actual);
    }
}
