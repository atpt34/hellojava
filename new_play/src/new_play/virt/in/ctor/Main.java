package new_play.virt.in.ctor;

class Mem {
    Mem() { System.out.println("Member");  }
    public void g() { System.out.println("Member g");  }
}

class Base {
    Base() { System.out.println("Base");    }
    Base(int a) { System.out.println("Base");  f();  }  // calls f() of Derived class, that is not constructed yet!
    public void f() { System.out.println("base f"); }
}

class Derived extends Base {
    private Mem mem;
    Derived() { System.out.println("Derived"); mem = new Mem(); }
    Derived(int a) { super(a); System.out.println("Derived"); mem = new Mem();  f(); }
    public void f() { System.out.println("derived f"); mem.g(); } // throws NullPointerExc if mem is not set i.e. null !!! 
}

public class Main {

    public static void main(String[] args) {
//        Derived d = new Derived();
        Derived d = new Derived(10);
    }

}
