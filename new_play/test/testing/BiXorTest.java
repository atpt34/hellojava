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

class PairsTriplesSearch {
    
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
    
    /*
     * Applicable for ints in range 0..31
     */
    static int getOneFromPairsBitmask(int[] arr) {
        int mask = IntStream.of(arr).reduce(0, (a, b) -> a ^= (1 << b));
        return Integer.numberOfTrailingZeros(mask);
    }
    
    /*
     * as above
     */
    static int getOnePairFromTriplesBitmask(int[] arr) {
        int mask = IntStream.of(arr).reduce(0, (a, b) -> a ^= (1 << b));
        int all = IntStream.of(arr).reduce(0, (a, b) -> a |= (1 << b));
//        System.out.println(Integer.toBinaryString(mask));
//        System.out.println(Integer.toBinaryString(all));
        return Integer.numberOfTrailingZeros(mask ^ all);
    }
}

public class BiXorTest {

    @Test
    public void testGetOneFromPairs() {
        assertEquals(1, PairsTriplesSearch.getOneFromPairs(new int[] {1, }));
        assertEquals(2, PairsTriplesSearch.getOneFromPairs(new int[] {1, 2, 1}));
        assertEquals(3, PairsTriplesSearch.getOneFromPairs(new int[] {1, 5, 3, 2, 1, 4, 2, 5, 4}));
        assertEquals(4, PairsTriplesSearch.getOneFromPairs(new int[] {11, 18, 95, 7, -333, 2, 11, 18, 2, 95, 4, -1, -333, -1, 7}));
    }

    @Test
    public void testGetOneFromPairsHashed() {
        assertEquals(1, PairsTriplesSearch.getOneFromPairsHashed(new int[] {1, }));
        assertEquals(2, PairsTriplesSearch.getOneFromPairsHashed(new int[] {1, 2, 1}));
        assertEquals(3, PairsTriplesSearch.getOneFromPairsHashed(new int[] {1, 5, 3, 2, 1, 4, 2, 5, 4}));
        assertEquals(4, PairsTriplesSearch.getOneFromPairsHashed(new int[] {11, 18, 95, 7, -333, 2, 11, 18, 2, 95, 4, -1, -333, -1, 7}));
    }
    
    @Test
    public void testGetOnePairFromTriples() {
        assertEquals(2, PairsTriplesSearch.getOnePairFromTriples(new int[] {1, 2, 1, 2, 1}));
        assertEquals(2, PairsTriplesSearch.getOnePairFromTriples(new int[] {1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4}));
        assertEquals(1, PairsTriplesSearch.getOnePairFromTriples(new int[] {3, 2, 3, 1, 3, 2, 1, 2}));
        assertEquals(2, PairsTriplesSearch.getOnePairFromTriples(new int[] {41, 77, -3, 2, -3, 41, -3, 77, 52, 41, 2, 52, 77, 52}));
    }
    
    @Test
    public void testGetOnePairFromTriplesStream() {
        assertEquals(2, PairsTriplesSearch.getOnePairFromTriplesStream(new int[] {1, 2, 1, 2, 1}));
        assertEquals(2, PairsTriplesSearch.getOnePairFromTriplesStream(new int[] {1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4}));
        assertEquals(1, PairsTriplesSearch.getOnePairFromTriplesStream(new int[] {3, 2, 3, 1, 3, 2, 1, 2}));
        assertEquals(2, PairsTriplesSearch.getOnePairFromTriplesStream(new int[] {41, 77, -3, 52, 2, -3, 41, -3, 77, 52, 41, 2, 52, 77}));
    }

    @Test
    public void testGetOneFromPairsBitmask() throws Exception {
        assertEquals(0, PairsTriplesSearch.getOneFromPairsBitmask(new int[] {0, }));
        assertEquals(0, PairsTriplesSearch.getOneFromPairsBitmask(new int[] {1, 0, 1}));
        assertEquals(3, PairsTriplesSearch.getOneFromPairsBitmask(new int[] {1, 5, 3, 2, 1, 4, 2, 5, 4}));
        assertEquals(4, PairsTriplesSearch.getOneFromPairsBitmask(new int[] {6, 11, 18, 25, 17, 3, 2, 11, 18, 2, 25, 4, 6, 13, 3, 13, 17}));
    }
    
    @Test
    public void testGetOnePairFromTriplesBitmask() throws Exception {
        assertEquals(0, PairsTriplesSearch.getOnePairFromTriplesBitmask(new int[] {0, 0}));
        assertEquals(0, PairsTriplesSearch.getOnePairFromTriplesBitmask(new int[] {1, 2, 0, 1, 2, 1, 2, 0}));
        assertEquals(2, PairsTriplesSearch.getOnePairFromTriplesBitmask(new int[] {1, 2, 1, 2, 1}));
        assertEquals(2, PairsTriplesSearch.getOnePairFromTriplesBitmask(new int[] {1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4}));
        assertEquals(1, PairsTriplesSearch.getOnePairFromTriplesBitmask(new int[] {3, 2, 3, 1, 3, 2, 1, 2}));
        assertEquals(17, PairsTriplesSearch.getOnePairFromTriplesBitmask(new int[] {18, 13, 2, 13, 9, 17, 18, 13, 2, 18, 2, 17, 9, 9}));
    }
}
