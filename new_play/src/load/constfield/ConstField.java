package load.constfield;

class Member {
    Member(String a) {
        System.out.println("member " + a);
    }
    static {
        System.out.println("member static");
    }
}

class Basis {
    Basis() {
        System.out.println("basis");
    }
    static {
        System.out.println("basis static");
    }
}
public class ConstField extends Basis {

    private static final Member sfm;
    private static Member sm;
    private final Member fm;
    private Member m;

    static {
        System.out.println("constfield static");
        sfm = new Member("static final");
        sm = new Member("only static");
        
        
    }
     
    {
        fm = new Member("only final");
        m = new Member("just instance");
    }
    
    ConstField() {
        System.out.println("constfield");
    }
    
    public static void main(String[] args) {
     
        new ConstField();
    }

}
