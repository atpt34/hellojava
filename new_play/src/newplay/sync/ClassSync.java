package newplay.sync;

public class ClassSync {
    
    private ClassSync(int a) {
//        synchronized (this) { // stupid! any thread creates its own instance !
        synchronized (ClassSync.class) { // now constructor synchronized!
            
            System.out.format("ctor %d start!\n", a);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.format("ctor %d end!\n", a);
        }
    }
    
    private static class UnsafeCtor {
        
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> new ClassSync(1111));
        Thread t2 = new Thread(() -> new ClassSync(2222));
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                new ClassSync(3333);
            }
        });
        class RunClass implements Runnable {
            @Override
            public void run() {
                new ClassSync(4444);
            }
        }
        Thread t4 = new Thread(new RunClass());
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
