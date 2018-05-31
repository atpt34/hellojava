package new_play.virt;

class B {
    public String field = "Base";  // non-virtual fields
    public void printField() { System.out.println(field); }  // virtual method
    public void f() { System.out.println("B f"); } // virtual method
    private void g() { System.out.println("B g"); } // non-virtual private methods
    public static void h() { System.out.println("B h"); } // non-virtual static methods
    public final void p() { System.out.println("B p"); } // non-virtual final methods
}

class D extends B {
    public String field = "Derived";
    public void printField() { System.out.println(field); 
    super.printField(); } // call to Base's method
    public void f() { System.out.println("D f"); }
    private void g() { System.out.println("D g"); }
    public static void h() { System.out.println("D h"); }
//    public final void p() { System.out.println("D p"); } // cannot override final method of B
}

public class Nonvirtual {

    public static void main(String[] args) {

         f();
         
        B b = new D();
        System.out.println(b.field);
        b.printField();
        b.f();
//        b.g();// private method !
        b.h();
        b.p();

    }

    private static void f() {
        System.out.println("main f()");
    }
}
