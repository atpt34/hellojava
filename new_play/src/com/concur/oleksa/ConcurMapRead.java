package com.concur.oleksa;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ConcurMapRead {

//    private static final int n = 5;    
//    private static Object obj0 = new Object();
//    private static Object obj1 = new Object();
//    private static Object obj2 = new Object();
//    private static Object obj3 = new Object();
//    private static Object obj4 = new Object();
//    private static Object[] objs = new Object[]{obj0, obj1, obj2, obj3, obj4};
    
    private static final int n = 1000000;
    private static Object[] objs = new Object[n];
    private static final int t = 2;
    private static long[] stats = new long[t];
    static {
        for (int i = 0; i < n; i++) {
            objs[i] = new Object();
        }
    }
    
    private static class Runner implements Runnable {

        private Map<String, Object> map;
        private int id;
        public Runner(int id, Map<String, Object> map) {
            this.id = id;
            this.map = map;
        }
        @Override
        public void run() {
            long start = System.nanoTime();
            for(int i = 0; i < n; ++i) {
                Object object = map.get(Integer.toString(i));
                if (object != objs[i]) {
                    System.out.println("incorrect read for i = " + i);
                }
            }
            long duration = System.nanoTime() - start;
            System.out.println("reads in " + (duration / 1000000) + " ms ");
            stats[id] = duration;
        }
        
    }

    public static void main(String[] args) {

//        Map<String, Object> map = new ConcurrentHashMap<>();
        Map<String, Object> map = new HashMap<>();
//        map.put("1", obj1);
//        map.put("2", obj2);
//        map.put("0", obj0);
//        map.put("3", obj3);
//        map.put("4", obj4);
        for (int i = 0; i < n; i++) {
            map.put(Integer.toString(i), objs[i]);
        }
        
        
        
        Thread[] ta = new Thread[t];
//        Runner runner = new Runner(map);
        for (int i = 0; i < ta.length; i++) {
            ta[i] = new Thread(new Runner(i, map));
        }
        for (int i = 0; i < ta.length; i++) {
            ta[i].start();
        }
        for (int i = 0; i < ta.length; i++) {
            try {
                ta[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long sum = 0;
        for (int i = 0; i < stats.length; i++) {
            sum += stats[i];
        }
        System.out.println("average: " + (sum / t /1000000) + " ms");
        /*
         * this test only reading from map (get method)!
         * 
         * for small # of values and threads
         * there are almost no difference !
         * 
         * if there are many values and threads there is little difference:
         * HashMap little slower for many reads, but stable timings
         * ConcurrentHashMap sometimes much slower than HM, but faster on average
         * 
         * Summary:
         *     /    # Objects   1000        10000   1000000   2500000  5000000
         * # Threads
         *      2               CHM 1ms     5ms     1000 ms   850 ms   6100 ms
         *                      HM 1ms      8ms     880 ms    850 ms   4750 ms
         *      
         *      32              CHM 0ms     6ms     1500 ms
         *                      HM 0ms      6ms     1900 ms
         * 
         * So if there are many values but little concurrent threads it's ok to use HashMap
         * otherwise use ConcurrentHashMap
         */
    }

}
