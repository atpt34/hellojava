package overriden.throwskeyword;

class Base {
    
    void f() throws Exception {
        System.out.println("Base::f");
    }
    
    void g() throws Exception {
        System.out.println("Base::f");
    }
    
    void wide() { }
    protected void wide2() { }
    public void narrow() { }
}

class Derived extends Base { // cannot `extends Base` if Base is `final class` !!!
    
    @Override
    void f() throws Exception { // error if not `throws` in Base::f 
        super.f();
        System.out.println("Derived::f");
        throw new Exception("Derived::f exception");
    }

    @Override
    void g() {  // ok, not to throw exception here !!!
        try {
            super.g();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void wide() {   super.wide();    } // ok, to wide access modifier
    @Override
//    void wide2() { } // error
    public void wide2() { }
//    @Override
//    void narrow() {        super.narrow();  } // error, to narrow access modifier ! Reduce visibility of inherited method
    
    
}

public class Tester {

    public static void main(String[] args) {
        
        Base b = new Base();
        Base b2 = new Derived();
        try {
            b.f();
            b2.f();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(f());
//      throw new IllegalArgumentException("hello from main"); // no `throws` or `try-catch` needed!
//      throw new RuntimeException(); // unchacked
      throw new ArrayIndexOutOfBoundsException();
      
//      throw new Exception("another hello"); // unhandled  excp
//      throw new Throwable();
    }

    static int f() {
        try {
            Base b = new Derived();
            b.f();
            return 1;
        } catch(Exception e) {
            e.printStackTrace();
            return 2;
        } finally {
            return 3;
        }
    }
}
