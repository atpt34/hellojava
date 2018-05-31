package overloading.crs;

class Parent {
    void f(double d) {
        System.out.println("parent");
    }
}

class Child extends Parent {
    void f(int a) {
        System.out.println("child");
    }
}

public class Hello {

    public static void main(String[] args) {
        int i1 = 9;
        int i2 = 3;
        call(i1); // ints ...
        call(i1,i2); // int
        
        
        Child c = new Child();
        c.f(13); // child
        c.f(13.5); // parent
        Parent p = new Child();
        p.f(13); // parent, doesn't know about overloaded methods
        p.f(13.5); // parent

    }
    
    static void call(int var0, int var1){
        System.out.println("int");
    }
    static void call(long var0, long var1){
        System.out.println("long");
    }
    static void call (int ... var){
        System.out.println("ints");
    }
    static void call (Integer var0,Integer var1){
        System.out.println("2Integer");
    }

}
