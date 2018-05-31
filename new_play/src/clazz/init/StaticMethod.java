package clazz.init;

class AleAleAle {
    private int a;
    public AleAleAle(int a) {
        this.a = a;
    }
    static void print() {
        System.out.println("static method with null ref");
    }
}

public class StaticMethod {

    public static void main(String[] args) {

        AleAleAle aaa = null;
        aaa.print(); // no NPEs
//        AleAleAle.print(); // correct invokation
        

    }

}
