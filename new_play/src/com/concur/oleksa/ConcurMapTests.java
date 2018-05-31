package com.concur.oleksa;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @see java.util.HashMap
 * @see java.util.concurrent.ConcurrentHashMap
 * 
 * @author atpt34
 *
 */
public class ConcurMapTests {
          
  private static class Runner implements Runnable {
      private Map<String, Integer> map;
    private final int limit;
      public Runner(Map<String, Integer> map) {
          this.map = map;
          limit = 100000;
    }
    @Override
    public void run() {
        Set<String> keySet = map.keySet();
        for (String s : keySet) {
            for(int i = 0; i < limit; i++) {
//                map.computeIfPresent(s, (k, v) -> ++v );
//                map.compute(s, (k, v) -> (v == null) ? 0 : ++v );
                map.compute(s, (k, v) -> (v == null) ? 0 : v + 10 );
//                map.compute(s, Runner::incr);
//                map.replace(s, map.get(s), map.get(s) + 1); // never atomic operation between get and get and replace !!!
             // this is done instead:
//                Integer oldVal, newVal; 
//                do {
//                  oldVal = map.get(s);
//                  newVal = (oldVal == null) ? 1 : (oldVal + 1);
//                } while (!map.replace(s, oldVal, newVal));
            }
        }
//        map.forEach((k, v) -> { for(int i = 0; i < limit; i++) { ++v;} });
//        for (String s : keySet) {
//            for(int i = 0; i < limit; i++) {
//                map.computeIfPresent(s, (k, v) -> { return --v; } );
//            }
//        }
        for(int i = 0; i < limit; i++) {
//            map.replaceAll((k, v) -> --v );
            map.replaceAll((k, v) -> v - 10 );
        }
    }
    
    private static int incr(String k, Integer v) {
        if (v == null) {
            return 0;
        }
        return v + 1;
    }

  }
  
  
  
    public static void main(String[] args) {
    
        Map<String, Integer> map = new HashMap<>();
//        Map<String, Integer> map = new ConcurrentHashMap<>(); // see remove and putIfAbsent
        
//        map.put("1", null); // can't do this in CHM !!!
        map.put("1", 2);
        map.put("2", 2);

        Thread t1 = new Thread(new Runner(map));
        Thread t2 = new Thread(new Runner(map));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println(map.get("1"));
        System.out.println(map.get("2"));
        
    }

}
