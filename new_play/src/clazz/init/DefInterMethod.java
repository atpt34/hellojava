package clazz.init;

interface Inter {
    public static final Inner i = new Inner();
    class Inner {
        static {
            System.out.println("Hello");
        }
    }
    void f();// { } // check with and w/o keyword default
    default void g() { } // triggers init of interface fields 
}

public class DefInterMethod implements Inter {

    public static void main(String[] args) {

    }

    @Override
    public void f() {
    }

}
