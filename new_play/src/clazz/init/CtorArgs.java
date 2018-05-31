package clazz.init;



public class CtorArgs {
    
    
    
    private static class A {
        static { System.out.println("A loaded"); }
        A() {        System.out.println("A");    }        
    }
    private static class  B {
        static { System.out.println("B loaded"); }
        B() {        System.out.println("B");    }
    }
    private static  class C {
        static { System.out.println("C loaded"); }
        C() {        System.out.println("C");    }
    }
    private static  class Ctor {
        static { System.out.println("Ctor loaded"); }
        Ctor(A a, B b, C c)   {        System.out.println("Ctor");    }
    }

    public static void main(String[] args) {

        Ctor ctor = new Ctor(new A(), new B(), new C());

       
        
        //BigDecimal
    }

}
