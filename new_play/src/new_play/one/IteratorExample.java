package new_play.one;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class IteratorExample {

    public static void main(String[] args) {

        List<Integer> arrayList = new ArrayList<>(10);
        arrayList.add(-2);
        arrayList.add(15);
        arrayList.add(5);
        arrayList.add(-8);
        arrayList.add(-24);
        arrayList.add(-25);
        arrayList.add(14);
        arrayList.add(1);
        arrayList.add(32);
        arrayList.add(7);
        arrayList.add(-1);
        arrayList.add(0);
        arrayList.add(-16);
        arrayList.add(10);
//        Collections.sort(arrayList, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                int bitCount1 = Integer.bitCount(o1);
//                int bitCount2 = Integer.bitCount(o2);
//                return bitCount1 - bitCount2;
//            }
//        });
        Collections.sort(arrayList, (a, b) ->  Integer.bitCount(a) - Integer.bitCount(b) );
        
        Map<Character, Integer> map = new HashMap<>();
        for (Iterator<Integer> iterator = arrayList.iterator(); iterator.hasNext();) {
            Integer integer = iterator.next();
            System.out.println(integer + " : " + Integer.toBinaryString(integer));    
            map.put((char)(int)integer, integer);
        }

        System.out.println("entry set of map");
        Set<Entry<Character,Integer>> entrySet = map.entrySet();
        Iterable<Entry<Character,Integer>> list = new ArrayList<>(entrySet);
        for (Entry<Character, Integer> entry : list) {
            System.out.println((int)entry.getKey() + " : " + entry.getValue());
        }
    }

}
