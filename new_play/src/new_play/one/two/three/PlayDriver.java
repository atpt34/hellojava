package new_play.one.two.three;

public class PlayDriver {
    
    public static int defInt;
    public static char defChar; // init by default with '\u0000'
    public static boolean defBool;

    public static void main(String[] args) {
        
        System.out.println(defChar);
        System.out.println('\u0000');
        System.out.println('\uFFFF');
        System.out.println(defInt);
        System.out.println(defBool);

        System.out.println("main ...");
        
        Object obj = null;
        System.out.println(obj); // prints null
//        System.out.println(obj.toString()); // NPE
        
        int a = 3;
        int b = 3;
        
        checkOverflow(a, b);
        checkOverflow(a, 4);
        checkOverflow(8, b);
        checkOverflow(6, 4);
        
        a = Integer.MAX_VALUE;
        b = Integer.MAX_VALUE;
        checkOverflow(a, b);
        
        byte bt = (byte) 129;
        System.out.println(bt);
        bt = (byte) 257;
        System.out.println(bt);
        bt = (byte) 384;
        System.out.println(bt);

        printComplement(1024);
        printComplement(-1024);
        printComplement(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE);
        printComplement(Integer.MIN_VALUE);
        System.out.println(Integer.MIN_VALUE);
     
        checkPhytagoras();
        
        checkArithmetic(257, 87);
        checkArithmetic(257, -87);
        checkArithmetic(-257, 87);
        checkArithmetic(-257, -87);
    }

    private static void checkOverflow(int a, int b) {
        System.out.println((a + b) / 2 == a / 2 + b / 2);
    }
    private static void printComplement(int a) {
        System.out.println(~a);
    }
    
    private static void checkPhytagoras() {
        int a = 3,        
            b = 4,
            c = 5;
//        System.out.println(a + ' ' + b + ' ' + c); // adds chars !!! 3 + 32 + 4 + 32 + 5 = 76
        System.out.println(a + " " + b + ' ' + c); 
        System.out.println((a*a + b*b) == c*c);
        double da = a / 10.0,
        db = b / 10.0,
        dc = c / 10.0;
//        System.out.println(da + ' ' + db + ' ' + dc); // adds chars !!!
        System.out.println(da + " " + db + ' ' + dc);
        System.out.println((da*da + db*db) == dc*dc);
        da = a / 100.0;
                db = b / 100.0;
                dc = c / 100.0;
         System.out.println(da + " " + db + ' ' + dc);
        System.out.println((da*da + db*db) == dc*dc);
    }
    
    private static void checkArithmetic(int a, int b) {
        int q = a / b; // division toward zero
        int r = a % b;
        boolean fl = a == q * b + r; // always true !!!
        System.out.println(q + " " + r + ' ' + fl);
    }

}
