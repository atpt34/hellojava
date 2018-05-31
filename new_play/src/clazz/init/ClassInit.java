package clazz.init;

/**
 * Only class's
 *  static final primitive or String fields(with = )
 *  init before super class loading 
 * @author atpt34
 *
 */

class Base {
    static {
        System.out.println("base");
        System.out.println(Derived.a);
        System.out.println(Derived.d);
        System.out.println(Derived.b);
        System.out.println(Derived.c);
        System.out.println(Derived.e);
        System.out.println(Derived.f);
        System.out.println(Derived.g);
        System.out.println(Derived.h);
    }
    
}
class Derived extends Base {

    static int a;
    static int d = 17;
    static final int b;
    static final int c = 13;
    static final String e;
    static final String f = "static final String";
    static final ClassInit g;
    static final ClassInit h = new ClassInit();
    static {
        System.out.println("derived");
        a = 10;
        b = 11;
        e =  "static final String in static block";
        g = new ClassInit();
        System.out.println(Thread.currentThread().getName());
    }
    
}


public class ClassInit {
    
    static {
        System.out.println("ClassInit loaded");
    }

    @Override
    public String toString() {
        return "ClassInit toString()";
    }

    public static void main(String[] args) {
        System.out.println("main...");

        Object obj = ClassInit.class;
        Object obj2 = ClassInit.class.getClassLoader();
        Object obj3 = Object.class.getClassLoader();
        Object obj4 = String.class.getClassLoader();
        System.out.println(obj);
        System.out.println(obj2);
        System.out.println(obj3);
        System.out.println(obj4);
        System.out.println(char.class);
        System.out.println(Character.class);
        System.out.println(Character.TYPE);
        System.out.println();

        //int a = Derived.a;
        new Derived(); new Derived(); // same effect
        
    }

}
