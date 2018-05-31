package new_play.pub.pri.meth;

class Parent {
    private void f() { System.out.println("private"); }
    
    public void g() { System.out.println("Parent g"); } // method
    public static void sg(Parent p) { p.g(); } // method inside static, effect is the same as g()
    
}

class Child extends Parent {
    public void f() { System.out.println("public"); // no duplication to Parent's f() !
    //super.f(); // not visible ! 
    }
    
    public void g() { System.out.println("Child g"); }
    public static void sg(Child p) { p.g(); }
}

public class Driver {

    public static void main(String[] args) {

        Child c = new Child();
        c.f();

        c.g();
        Child.sg(c);
    }

}
