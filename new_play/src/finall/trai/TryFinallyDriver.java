package finall.trai;

class One {
    void open() {
        System.out.println("open One");
    }
    void f() {
        throw new RuntimeException();
    }
    void g() {
        
    }
    void close() {
        System.out.println("close One");
    }
}

class Two implements AutoCloseable {
    
    void open() {
        System.out.println("open Two");
    }

    @Override
    public void close() throws Exception {

        System.out.println("close Two");
        
    }
    void f() {
        throw new RuntimeException();
    }
    void g() {
        
    }
}

public class TryFinallyDriver {

    public static void main(String[] args) throws Exception {
        
        Two t = null;
        System.out.println(t instanceof AutoCloseable);
        t = new Two();
        System.out.println(t instanceof AutoCloseable);

        One one = null;
        try {
            one = new One();
            one.open();
            one.f();
            one.g();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (one != null) {
                one.close();
            }            
        }

        System.out.println("continue ...");
         
        try(Two o = new Two()) { // try with resources must implement AutoCloseable!
            o.open();
            o.f();
        } 
//        catch(Exception e) {
//            e.printStackTrace();
//        }
    }

}
