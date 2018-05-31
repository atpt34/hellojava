package stack.trace;

class One {
    static void f() {
        throw new RuntimeException("just throw for fun!");
//        throw new Object("just throw for fun!"); // not subclass Throwable
//        throw new Error("end of the game!");
    }
}
class Two {
    static void f() {
        One.f();
    }
}
class Three {
    static void f() {
        Two.f();
    }
}

public class Thrower {

    public static void main(String[] args) {
//        try {
            Three.f();
//        }catch (RuntimeException e) {
//            System.out.println(e);
//        }
        
    }

}
