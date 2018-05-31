package com.concur.oleksa;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SyncQueueExample {

    

    public static void main(String[] args) {

        SynchronousQueue<Object> synchronousQueue = new SynchronousQueue<Object>();
//        try {
            /*
             * will fail in one thread
            System.out.println("main thread: ");
            final boolean offer = synchronousQueue.offer(new Object(), 1, TimeUnit.SECONDS);
            System.out.println(offer);
            final Object poll = synchronousQueue.poll(1, TimeUnit.SECONDS);
            System.out.println(poll);
            final boolean offer2 = synchronousQueue.offer(new Object());
            System.out.println(offer2);
            final Object poll2 = synchronousQueue.poll();
            System.out.println(poll2);*/
            
            System.out.println("two parallel threads: ");
            int limit = 10;
            
            new Thread(new Runnable() {                
                @Override
                public void run() {
                    try {                        
                        for (int i = 0; i < limit; i++) {
                            Object object = new Object();
                            boolean offer = synchronousQueue.offer(object, 1, TimeUnit.MILLISECONDS);
                            System.out.println("offered succeed: " + offer + " on object: " + object);
                        }                        
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }                    
                }
            }).start();
            new Thread(new Runnable() {               
                @Override
                public void run() {
                        try {
                            for (int i = 0; i < limit; i++) {
                                Object poll = null;
                            poll = synchronousQueue.poll(1, TimeUnit.MILLISECONDS);
                            System.out.println("result of poll: " + poll);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
            }).start();
            
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

    }

}
