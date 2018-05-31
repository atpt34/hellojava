package newplay.sync;

import overloading.crs.Hello;

public class AnotherQuest {

    public static void main(String[] args) {

        Outer outer = new Outer().new Inner();
        outer.print();

//        new Hello(); // so default ctors are: public !
//        new QuestConcur();
        
        String s;
        if ((s = "java") == "java") {
            System.err.println("true");
        }
    }
    
    private static void main(String s) {} // ok
//    private static void main(String... s) {} // error
    
    static class Outer {
//    class Outer { // instance of AnotherQuest needed !
        static int a = 100;
        static final int b = 100;
        
        public void print() {}
        
        class Inner extends Outer { //  not static class !
//            static int c = 200;
            static final int b = 100;
            @Override
            public void print() { System.out.println("hello"); Outer o = Outer.this; }
//            class MoreInner extends Inner { }
        }
        
    }
    
    /// just banch of member types:
    private static abstract class AbstractC {      }
    private class InnerClass extends AbstractC {       }
    private static interface InInter { }
    private static @interface MyAnnot { }
    private static enum HelloE implements InInter { }
    private static class NestedClass extends AnotherQuest implements InInter { } 

}
