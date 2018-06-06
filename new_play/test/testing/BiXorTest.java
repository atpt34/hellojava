package testing;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

class BiXor {
    
    static int getOneFromPairs(int[] arr) {
        int res = 0;
        for (int a : arr) {
            res ^= a;
        }
        return res;
    }
    
    static int getOneFromPairsHashed(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            map.merge(a, 1, (k, v) -> v = v + 1);
        }
        for(Entry<Integer, Integer> e : map.entrySet()) {
            if (e.getValue() == 1) {
                return e.getKey();
            }
        }
        throw new RuntimeException("no value");
    }
    
    static int getOnePairFromTriples(int[] arr) {
        Set<Integer> set = new HashSet<>();
        OptionalInt actual = IntStream.of(arr).peek(set::add).reduce((a, b) -> a ^ b);
        Optional<Integer> reduce = set.stream().reduce((a, b) -> a ^ b);
        return actual.getAsInt() ^ reduce.get();
    }
    
    static int getOnePairFromTriplesStream(int[] arr) {
        Map<Integer, Long> collect = IntStream.of(arr).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return collect.entrySet().stream().filter(e -> e.getValue() == 2).findFirst().get().getKey();
    }
}

public class BiXorTest {

    @Test
    public void testGetOneFromPairs() {
        assertEquals(1, BiXor.getOneFromPairs(new int[] {1, }));
        assertEquals(2, BiXor.getOneFromPairs(new int[] {1, 2, 1}));
        assertEquals(3, BiXor.getOneFromPairs(new int[] {1, 5, 3, 2, 1, 4, 2, 5, 4}));
        assertEquals(4, BiXor.getOneFromPairs(new int[] {11, 18, 95, 7, -333, 2, 11, 18, 2, 95, 4, -1, -333, -1, 7}));
    }

    @Test
    public void testGetOneFromPairsHashed() {
        assertEquals(1, BiXor.getOneFromPairsHashed(new int[] {1, }));
        assertEquals(2, BiXor.getOneFromPairsHashed(new int[] {1, 2, 1}));
        assertEquals(3, BiXor.getOneFromPairsHashed(new int[] {1, 5, 3, 2, 1, 4, 2, 5, 4}));
        assertEquals(4, BiXor.getOneFromPairsHashed(new int[] {11, 18, 95, 7, -333, 2, 11, 18, 2, 95, 4, -1, -333, -1, 7}));
    }
    
    @Test
    public void testGetOnePairFromTriples() {
        assertEquals(2, BiXor.getOnePairFromTriples(new int[] {1, 2, 1, 2, 1}));
        assertEquals(2, BiXor.getOnePairFromTriples(new int[] {1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4}));
        assertEquals(1, BiXor.getOnePairFromTriples(new int[] {3, 2, 3, 1, 3, 2, 1, 2}));
        assertEquals(2, BiXor.getOnePairFromTriples(new int[] {41, 77, -3, 2, -3, 41, -3, 77, 52, 41, 2, 52, 77, 52}));
    }
    
    @Test
    public void testGetOnePairFromTriplesStream() {
        assertEquals(2, BiXor.getOnePairFromTriplesStream(new int[] {1, 2, 1, 2, 1}));
        assertEquals(2, BiXor.getOnePairFromTriplesStream(new int[] {1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4}));
        assertEquals(1, BiXor.getOnePairFromTriplesStream(new int[] {3, 2, 3, 1, 3, 2, 1, 2}));
        assertEquals(2, BiXor.getOnePairFromTriplesStream(new int[] {41, 77, -3, 52, 2, -3, 41, -3, 77, 52, 41, 2, 52, 77}));
    }

}
