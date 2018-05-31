package com.concur.oleksa;

public class InterruptedDriver {

    public static void main(String[] args) {

        Thread t = new Thread( new Runnable() {            
            @Override
            public void run() {
           
                
                int i = 1;
//                while(!Thread.interrupted()) {
                while(!Thread.currentThread().isInterrupted()) {
                    System.out.println(i++);                    
                }
            }
        });
        t.start();
        
        try {
            Thread.sleep(10);
            t.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        

    }

}
