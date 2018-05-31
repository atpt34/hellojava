package wrap.primitive.types;

public class WrapperHashCodeDriver {

    public static void main(String[] args) {

//        java.util.Objects
//        java.util.Optional
        System.out.println(null == null);
        Object o1 = null, o2 = null;
        System.out.println(o1 == o2);
        
        Integer i = new Integer(13);
        int ii = i;
        i = null;
        ii = i; // NPE
        
//        Integer i = new Integer(18);
//        System.out.println(i == i.hashCode());
////        i = 16;
////        System.out.println(i == i.hashCode());
////        i = Integer.MIN_VALUE;
////        System.out.println(i == i.hashCode());
//        Double d = new Double(13.33e+14);
//        System.out.println(d == d.hashCode());
        
        Number[] numbers = new Number[]{ 
                new Byte((byte) 127),
                new Short((short) 65535),
//                new Character('\uffff'),
//                new Boolean(true), 
                new Integer(-16),
                new Long(32L),
//                new Long(-32L),
                0x80000000L,
//                new Float(11.13f),
                new Float(0f), 2.e+30f,
//                new Double(17.19) 
//                new Double(0)
                0.0, 2e+65, 0.625
                };
        for (Number num : numbers) {
            System.out.println(num.intValue() == num.hashCode());
        }
        
        // all true :
//        System.out.println("Byte: " + testBytesHashCode());
//        System.out.println("Integer: " + testIntegerHashCode());
//        System.out.println("Short: " + testShortHashCode());
        
        
        
    }

    private static boolean testBytesHashCode() {
        for (byte i = Byte.MIN_VALUE; i < Byte.MAX_VALUE; i++) {
//        for (byte i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) { // infinite loop
//        for (byte i = Byte.MAX_VALUE; i > Byte.MIN_VALUE; i--) {
            Byte wi = i;
            if (wi.hashCode() != wi) {
                return false;
            }
//            System.out.println(wi);
        }
        Byte b = Byte.MAX_VALUE;
        if (b != b.hashCode()) return false;
        return true;
    }
    private static boolean testIntegerHashCode() {
        for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
            Integer wi = i;
            if (wi.hashCode() != wi) {
                return false;
            }
        }
        Integer b = Integer.MAX_VALUE;
        if (b != b.hashCode()) return false;
        return true;
    }
    private static boolean testShortHashCode() {
        for (short i = Short.MIN_VALUE; i < Short.MAX_VALUE; i++) {
            Short wi = i;
            if (wi.hashCode() != wi) {
                return false;
            }
        }
        Short b = Short.MAX_VALUE;
        if (b != b.hashCode()) return false;
        return true;
    }

}
