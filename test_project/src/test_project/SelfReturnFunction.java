package test_project;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Consumer;

public class SelfReturnFunction {

    public static class FunctionSelfReturn {
        public FunctionSelfReturn() {        }
        @SuppressWarnings("rawtypes")
        public static Supplier<Supplier> f() {
            System.out.println("f");
            return FunctionSelfReturn::f;
        }
    }
    
    public static Supplier<Object> g() {
        System.out.println("g");
        return SelfReturnFunction::g;
    }
    
    public static Function<Integer, Object> h(int n) {
        System.out.println("h " + n);
        return SelfReturnFunction::h;
    }
    
    @SuppressWarnings("rawtypes")
    public static Function hh(Object obj) {
        System.out.println("hh " + obj);
        return SelfReturnFunction::hh;
    }
    
    public static void r(Consumer<Object> c) {
        System.out.println("r");
//        c.accept(c); // stack overflow
    }
    
    public static void rr(Object c) {
        System.out.println("rr");
        Consumer<Object> f = (Consumer<Object>) c;
        f.accept(f);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void main(String[] args) {

        Supplier<Object> f = SelfReturnFunction::g;
        Supplier<Object> g = SelfReturnFunction.FunctionSelfReturn::f;
        Function<Integer, Object> h = SelfReturnFunction::h;
        Function v = SelfReturnFunction::hh;
        Consumer<Consumer> u = SelfReturnFunction::r;
        Consumer<Object> w = SelfReturnFunction::rr;
        
        int n = 4;
        for (int i = 0; i < n; i++) {
            f = (Supplier<Object>) f.get();
            g = (Supplier<Object>) g.get();
            h = (Function<Integer, Object>) h.apply(i);
            v = (Function) v.apply(String.valueOf(i));
            u.accept(u);
            w.accept(u);
        }
    }

}
