package clazz.init;

public class NewThreadInStaticBlock {

    private static long x = -1;
    
    private static final Object lock = new Object();
    
    static {
        System.out.println("static start");
        x = 3;
        Thread t = new Thread(() -> {System.out.println("new thread"); x = 17;
        synchronized (lock) {lock.notify(); } System.out.println("new thread end");});
        t.start();
//        try {
//            t.join(); // deadlock, any new thread see this class as not initialized, so can't do any assignment!
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        try {
            synchronized (lock) { // same problem
                lock.wait();
            }
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("static end");
    }
    
    public static void main(String[] args) {
        System.out.println("main");
        System.out.println("x = " + x);
    }

}