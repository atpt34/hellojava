package refactoring.newplay;

abstract class A {
    /**
     * @param t
     * @param obj TODO
     * @return
     */
    public static int Af(int t, Object obj) {
        int veryLongAndHardToWorkWithIntVariable = t--;
        int d = veryLongAndHardToWorkWithIntVariable * veryLongAndHardToWorkWithIntVariable;
        while (t-- > 0) {
            d += veryLongAndHardToWorkWithIntVariable;
            ++veryLongAndHardToWorkWithIntVariable;
        }
        return veryLongAndHardToWorkWithIntVariable;
    }
}

class B extends A {
    public static class ABData {
        public int a;
        public int b;

        public ABData() {
        }
    }

    private ABData abdata = new ABData();
}

class C extends A implements Methodable {

    private int b;

    /*
     * (non-Javadoc)
     * 
     * @see refactoring.newplay.Methodable#f(java.lang.String)
     */
    @Override
    public void f(String str) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see refactoring.newplay.Methodable#g(double)
     */
    @Override
    public int g(double d) {
        return (int) d;
    }
    
    public void addB(int c) {
        setB(getB() + c);
    }

    private int getB() {
        return b;
    }

    private void setB(int b) {
        this.b = b;
    }
}

public class RefactorDriver {

    public static void main(String[] args) {

        Methodable m = new C();
        Methodable.f();
        A ab = new B();
        double a = 6.0;
        double b = 0.5;
        System.out.println(a / b);
        System.out.println(a % b);
        System.out.println((a%b) == (a - (a/b)*b));
    }

}
