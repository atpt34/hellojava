package com.concur.oleksa;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class StackOverflowExample {

    public static void main(String[] args) {
        final AtomicInteger activeProducers=new AtomicInteger();
        final BlockingQueue<Integer> queue=new ArrayBlockingQueue<>(10);
        Runnable producer=new Runnable() {
          public void run() {
            try {
              for(int i=0; i<10; i++) {
                  Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                  queue.put(i);
                  System.out.println("Produced "+i);
              }
            } catch(InterruptedException ex) {
              System.err.println("producer terminates early: "+ex);
            }
            finally { activeProducers.decrementAndGet(); }
          }
        };
        Runnable consumer=new Runnable() {
          public void run() {
            try {
              for(;;) {
                Integer queueElement = queue.poll(1, TimeUnit.SECONDS);
                if(queueElement!=null)
                  System.out.println("Consumed : " + queueElement);
                else if(activeProducers.get()==0 && queue.peek()==null) return;
              }
            } catch(InterruptedException ex) {
              System.err.println("consumer terminates early: "+ex);
            }
          }
        };
        final int NUM_PRODUCERS = 3, NUM_CONSUMERS = 2;
        for(int i=0; i<NUM_PRODUCERS; i++) {
          activeProducers.incrementAndGet();
          new Thread(producer).start();
        }
        for(int i=0; i<NUM_CONSUMERS; i++) {
          new Thread(consumer).start();
        }

    }// Objects Optional UUID

}
