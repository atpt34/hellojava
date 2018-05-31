package refactoring.newplay;

interface Methodable {

    void f(String str);

    int g(double d);

    static void f() { // interfaces can have static methods !!!
        System.out.println("f");;
    }
}