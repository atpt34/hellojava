package com.concur.oleksa;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Threads can be ordered with map and just atomic int.
 * Do not do this, since it is clearly sequential task!
 *  
 * @author atpt34
 *
 */
class Orderer implements Runnable {

//    private static int n = 1;
    private static AtomicInteger a = new AtomicInteger(1);
    private static Map<String, Integer> order;
    static {
        order = new HashMap<>();
        order.put("1", 1);
        order.put("2", 2);
        order.put("3", 3);
        order.put("4", 4);
        order.put("5", 5);
        order.put("6", 6);
        order.put("7", 7);
        order.put("8", 8);
    }
    private static Object lock = new Object();
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        int i = order.get(threadName);
//        synchronized (lock) {
//            while (i != n) {
            while (i != a.get()) {
                try {
                    synchronized (lock) {
                        lock.wait();
                    }
//                    lock.wait(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//            n++;
            a.incrementAndGet();
            synchronized (lock) {
                lock.notifyAll();
                System.out.println(threadName);
            }
//            lock.notifyAll();
//            System.out.println(threadName);
//        }
    }
    
}
public class ThreadOrder {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Orderer orderer = new Orderer();
        Thread thread1 = new Thread(orderer, "1");
        Thread thread2 = new Thread(orderer, "2");
        Thread thread3 = new Thread(orderer, "3");
        Thread thread4 = new Thread(orderer, "4");
        Thread thread5 = new Thread(orderer, "5");
        Thread thread6 = new Thread(orderer, "6");
        Thread thread7 = new Thread(orderer, "7");
        Thread thread8 = new Thread(orderer, "8");
        thread5.start();
        thread3.start();
        thread2.start();
        thread7.start();
        thread6.start();        
        thread4.start();        
        thread8.start();
        thread1.start();
        try {
            thread1.join(10);
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
            thread6.join();
            thread7.join();
            thread8.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
