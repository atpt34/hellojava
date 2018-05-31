package strings;

class A {
    int a;
    private int b;
}
/**
 * 
 * @author Oleksii Tretinichenko
 *
 */
public class StringDriver {

    public static void main(String[] args) {

        //A a = new A(298, 239); no such constructor, only default
        
        String str1 = "string";
        String str2 = "string";
        String str3 = new String("string");
        String str33 = new String("string");
        
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        System.out.println(str33 == str3);
        
        String str4 = str3.intern();

        System.out.println(str4 == str3);
        System.out.println(str1 == str4);
        System.out.println(str1 == str3);
    }

    /**
     * 
     * @param stringDriver
     * @param a
     * @param b
     * @return
     */
    public static boolean isDivisibleDoublesIndirection(StringDriver stringDriver, double a, double b) {
        return stringDriver.isDivisibleDoubles(a, b);
    }


    /**
     * Method to test!
     * 
     * @param a
     * @param b
     * @return
     */
    public boolean isDivisibleDoubles(double a, double b) {
        return (a + b) / 2 == a / 2 + b / 2;
    }
}
