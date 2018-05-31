package newplay.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuestConcur {

    // default ctor is public
    
    public static void main(String[] args) {

        ExecutorService exec = Executors.newFixedThreadPool(2);
        
        for (int i = 0; i < 6; ++i)
            exec.execute(new InThread());
        
        exec.shutdown();
        while(!exec.isTerminated()) {}

    }
    
    private static class InThread implements Runnable {

        @Override
        public void run() {

                System.out.println("running...");
            
        }
        
    }

}
