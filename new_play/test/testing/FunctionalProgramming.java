package testing;

import static org.junit.Assert.*;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import org.junit.Test;

class Adder {
    
    private int sum;
    
    Adder() {
        sum = 0;
    }
    
    Adder(int init) {
        sum = init;
    }
    
    // mutator method !!!
    Adder add(int a) {
        sum += a;
        return this;
    }

    int getSum() {
        return sum;
    }
    
    // not very useful methods
    Function<Integer, Adder> getAdd() {
        return this::add;
    }
    
    static Supplier<Adder> getCtor() {
        return Adder::new;
    }
}

/*
 * Immutable adder
 */
class Adder2 {
    
    private int sum;
    
    Adder2() {
        sum = 0;
    }
    
    Adder2(int init) {
        sum = init;
    }
    
    Adder2 add(int a) {
        return new Adder2(sum + a);
    }
    
    int getSum() {
        return sum;
    }
}

public class FunctionalProgramming {
 
    @Test
    public void testAdd() throws Exception {
        Adder adder = new Adder();
        int actual = adder.add(1).add(2).add(3).add(5).add(7).getSum();
        int expected = 18;
        assertEquals("addition failed", expected, actual);
        assertNotEquals("mutation failed", 0, adder.getSum());
    }
    
    @Test
    public void testAddStreams() throws Exception {
        int init = 500000;
        Adder adder = new Adder(init);
        int[] values = new int[]{0, 10, 200, 3000, 40000};
        IntStream.of(values).boxed().forEach(adder::add);
        int actual = adder.getSum();
        int expected = IntStream.of(values).reduce(init, Integer::sum);
        assertEquals("addition failed", expected, actual);
    }
    
    @Test
    public void testAdder2() throws Exception {
        Adder2 adder = new Adder2(2);
        int actual = adder.add(1).add(2).add(3).add(5).add(7).getSum();
        int expected = 20;
        assertEquals("addition failed", expected, actual);
        assertEquals("immutability failed", 2, adder.getSum());
    }
    
    @Test
    public void testAdder2Streams() throws Exception {
        int init = 500000;
        Adder2 adder = new Adder2(init);
        int[] values = new int[]{0, 10, 200, 3000, 40000};
        int expected = 543210;
        Adder2 actual = IntStream.of(values)
            .mapToObj(Adder2::new)
            .reduce(adder, (x, y) -> new Adder2(x.getSum() + y.getSum()));
        
        assertEquals("addition failed", expected, actual.getSum());
        assertEquals("mutation occured", init, adder.getSum());
    }
    
    @Test
    public void testAddNew() throws Exception {
        Adder adder = Adder.getCtor().get();
        Function<Integer, Adder> function = adder.getAdd();
        function.apply(1);
        function.apply(5);
        function.apply(23);
        int expected = 29;
        assertEquals("addition failed", expected, adder.getSum());
    }
    

}
