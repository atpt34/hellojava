package enumeration.example;

public enum EnumDriver {

    USD(25) { // anonymous classes !!!!
        @Override
        public int calc(int sum) {
            return this.getRate() * sum;
        }
    }, 
    EURO(30) {
        @Override
        public int calc(int sum) {
            return getRate() * sum + 1;
        }
    },
    UAH(1) {
        @Override
        public int calc(int sum) {
            return this.getRate() * sum - 1;
//            return rate; // cannot make static ref to nonstatic field!
        }
    };
    
    
    private int rate;
    
    /* can declare and override abstract methods in enum! */
    public abstract int calc(int sum);
    
    private EnumDriver(int r) {
        setRate(r);
    }
    
    public String toString() {
        return super.toString() + " and rate " + getRate();
    }
    
    public int getRate() {
        return rate;
    }

    private void setRate(int rate) {
        this.rate = rate;
    }

    enum Answer {
        Yes, No, Maybe;
    }
    
    public static void main(String[] a) {
//        main(a); // lol, recursive main!!!
        
        EnumDriver[] values = EnumDriver.values();
        for (EnumDriver e : values) {
            System.out.println(e);
            System.out.println(e.getRate());
            System.out.println(e.calc(100));
        }
        values[0] = EnumDriver.UAH;
        for (EnumDriver e : values) {
            System.out.println(e);
            System.out.println(e.getRate());
            System.out.println(e.calc(100));
        }
        for(EnumDriver e : EnumDriver.values()) {
            System.out.println(e);
        }
        
        EnumDriver x1 = EnumDriver.valueOf("UAH");
        System.out.println(x1);
        System.out.println(x1.getClass()); // class enumeration.example.EnumDriver$3
        System.out.println(x1.getDeclaringClass()); // class enumeration.example.EnumDriver
        EnumDriver x2 = EnumDriver.valueOf("USD");
        System.out.println(x2);
        System.out.println(x2.getClass()); // class enumeration.example.EnumDriver$1
        System.out.println(x2.getDeclaringClass()); // class enumeration.example.EnumDriver
        System.out.println(x1 == x2); // f
        System.out.println(x2.getClass() == x1.getClass()); // f
        System.out.println(x1.getDeclaringClass() == x2.getDeclaringClass()); // t
        
        Answer ans = Answer.valueOf("Yes");
        System.out.println(ans);
        System.out.println(ans.getClass()); // class enumeration.example.EnumDriver$Answer
        System.out.println(ans.getDeclaringClass());// class enumeration.example.EnumDriver$Answer
        Answer ans2 = Answer.valueOf("No");
        System.out.println(ans2);
        System.out.println(ans2.getClass());// class enumeration.example.EnumDriver$Answer
        System.out.println(ans2.getDeclaringClass());// class enumeration.example.EnumDriver$Answer
        System.out.println(ans == ans2); // f
        System.out.println(ans.getClass() == ans2.getClass()); // t
        System.out.println(ans.getDeclaringClass() == ans2.getDeclaringClass());// t
    }
}
