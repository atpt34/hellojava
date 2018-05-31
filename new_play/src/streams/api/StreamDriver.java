package streams.api;

import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Dummy {
    private int a;
    private int b;
    public int getA() {
        return a;
    }
    public void setA(int a) {
        this.a = a;
    }
    public int getB() {
        return b;
    }
    public void setB(int b) {
        this.b = b;
    }
    public Dummy(int a, int b) {
        this.a = a;
        this.b = b;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Dummy [a=");
        builder.append(a);
        builder.append(", b=");
        builder.append(b);
        builder.append("]");
        return builder.toString();
    }
    @Override
    public int hashCode() {
        return a + b;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Dummy other = (Dummy) obj;
        if (a != other.a)
            return false;
        if (b != other.b)
            return false;
        return true;
    }
    
}
public class StreamDriver {

    public static void main(String[] args) {
        
        Stream.of(new Object(), new String("ojb"), "hello").map(Object::hashCode).sorted().forEach(System.out::println);
        Stream.of(new Object(), new String("ojb"), "hello").forEach(StreamDriver::action);
//        Stream.iterate(2L, x -> x * x).limit(7).forEach(System.out::println);
//        Stream.of("10", "11", "32")    .map(x -> Integer.parseInt(x, 16))           .forEach(System.out::println);
//        IntStream.range(0, 10).forEach(System.out::println);
        System.out.println("Sorted stream of distinct ints");
        Stream.of(29, 239, 293, -1, -2, 043, 0x82, 329, 329, 329, -3, 100, -1, 99, 239).sorted().distinct().forEach(System.out::println);
        
//        long count = IntStream.range(0, 10)
//                .flatMap(x -> IntStream.range(0, x))
//                .peek(System.out::println)
//                .count();
//            System.out.println(count);
        
//        Stream.of("aa", "aA", "Aa", "AA", "bb", "bB", "Bb", "BB").map(Object::hashCode).sorted().forEach(System.out::println);
        HashSet<String> hs = new HashSet<String>(); 
        Stream.of("aa", "aA", "Aa", "AA", "bb", "bB", "Bb", "BB").forEach(hs::add);
        hs.forEach(System.out::print);
//        Stream.of(hs).sorted().forEach(System.out::print);
//        System.out.println(hs);
        
        Hashtable<String, String> ht = new Hashtable<>(hs.size());
        for (String key : hs) {
            ht.put(key, key);
        }
        System.out.println(ht);
        
        Dummy d1 = new Dummy(4, 2);
        Dummy d2 = new Dummy(2, 4);
        Dummy d3 = new Dummy(2, 2);
        Dummy d4 = new Dummy(2, 4);
        System.out.println(d2.equals(d4) + " " + d2.hashCode() + " " + d4.hashCode());
        System.out.println(d1.equals(d2) + " " + d1.hashCode() + " " + d2.hashCode());
     
        HashSet<Dummy> hd = new HashSet<Dummy>();
        Stream.of(d1, d2, d3, d4).forEach(hd::add);
//        Stream.of(hs).sorted().forEach(System.out::print);
        System.out.println(hd);
        
        final Dummy d5 = new Dummy(38, 29);
        d5.toString();
        d5.setA(293); // ok
//        d5 = d4; // d5 connot be reassigned
        
        HashSet<Integer> hs2 = new HashSet<Integer>(16); 
        Stream.of(18, 34, 16, 18, 50).forEach(hs2::add);
        hs2.forEach(System.out::print);
        
        //ArrayList
    }
    
    public static void action(Object ... objs) {
        for (Object object : objs) {
            System.out.println(object.hashCode());
        }
    }

}
