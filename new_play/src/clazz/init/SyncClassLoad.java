package clazz.init;

/*
 * A B C D classes loaded by one thread(any thread)
 * X Y Z classes loaded by one thread and can be different from prev thread that init ABCD 
 * 
 * So class and all it's superclasses init by one thread.
 */

class A {
    static { System.out.println("A " + Thread.currentThread().getName()); 
    try {        Thread.sleep(1000);    } catch (InterruptedException e) {        e.printStackTrace();    }}
    { System.out.println("new D " + Thread.currentThread().getName()); }
}
class B extends A {
    static { System.out.println("B " + Thread.currentThread().getName()); 
    try {        Thread.sleep(1000);    } catch (InterruptedException e) {        e.printStackTrace();    }}
//    { System.out.println("new B " + Thread.currentThread().getName()); }
}
class C extends B {
    static { System.out.println("C " + Thread.currentThread().getName()); 
    try {        Thread.sleep(1000);    } catch (InterruptedException e) {        e.printStackTrace();    }}}
class D extends C {
    static { System.out.println("D " + Thread.currentThread().getName()); 
    try {        Thread.sleep(1000);    } catch (InterruptedException e) {        e.printStackTrace();    }}}

// class X { // by diff threads
class X extends D{
    static { System.out.println("X " + Thread.currentThread().getName()); 
    try {        Thread.sleep(1000);    } catch (InterruptedException e) {        e.printStackTrace();    }}
    { System.out.println("new Z " + Thread.currentThread().getName()); }}
class Y extends X {
    static { System.out.println("Y " + Thread.currentThread().getName()); 
    try {        Thread.sleep(1000);    } catch (InterruptedException e) {        e.printStackTrace();    }}}
class Z extends Y {
    static { System.out.println("Z " + Thread.currentThread().getName()); 
    try {        Thread.sleep(1000);    } catch (InterruptedException e) {        e.printStackTrace();    }}
    // ctor like static method and it's not synchronized !!!
    Z() {try {        Thread.sleep(1000);    } catch (InterruptedException e) {        e.printStackTrace();    }}}

public class SyncClassLoad {

    private static final int N = 5;

    public static void main(String[] args) {
        
        final Runnable target = () -> { new D(); new Z(); };
        Thread[] ta = new Thread[N];
        for (int i = 0; i < ta.length; i++) {
            ta[i] = new Thread(target);
        }
        for (Thread t : ta) {
            t.start();
        }
            // sleep main
//        try {        Thread.sleep(1000);    } catch (InterruptedException e) {        e.printStackTrace();    }
//        new D();
    }

}
