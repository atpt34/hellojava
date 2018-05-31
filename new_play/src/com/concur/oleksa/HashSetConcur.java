package com.concur.oleksa;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

class Runner implements Runnable {

    private Set<Integer> set;
    private int limit = 100;
    private int yes = 0, no = 0;
    
    public Runner(Set<Integer> set) {
        this.set = set;
    }
    @Override
    public void run() {
        for (int i = 0; i < limit; i++) {
            if(set.add(i)) {
                yes++;
            } else {
                no++;
            }
        }
    }
    
    public int getYes() { return yes; }
    public int getNo() { return no; }
    
}
public class HashSetConcur {

    
    public static void main(String[] args) {

        Set<Integer> hashSet = new HashSet<>();
//        Set<Integer> hashSet = Collections.synchronizedSet(new HashSet<>()); // sync wrapper
        
        
//        Set<Integer> hashSet = new ConcurrentHashMap<Integer, Boolean>().keySet(false);
//        Set<Integer> hashSet = ConcurrentHashMap.newKeySet();

        Runner runner = new Runner(hashSet);
        Runner runner2 = new Runner(hashSet);
        Thread t1 = new Thread(runner);
        Thread t2 = new Thread(runner2);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
            System.out.println(runner.getYes());
            System.out.println(runner.getNo());
            System.out.println(runner2.getYes());
            System.out.println(runner2.getNo());
            System.out.println(hashSet.size());
            int prev = -1;
            for (int i : hashSet) {
                if (i == prev) {
                    System.out.println(i);
                }
                prev = i;
            }
            System.out.println(hashSet);
            System.out.println(hashSet.size());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
