package new_play.swap.ints;

class Pair {
    private int a;
    private int b;
    public Pair(int a, int j) {
        this.a = a;
        b = j;
    }
    int getA() {
        return a;
    }
    void setA(int a) {
        this.a = a;
    }
    int getB() {
        return b;
    }
    void setB(int b) {
        this.b = b;
    }
    
    @Override
    public String toString() {
        return "Pair [a=" + a + ", b=" + b + "]";
    }
    /*
     * This approach is the best, because
     * there is no integer overflow or underflow,
     * and additionally no temporary vars.
     */
    void swap() {
        a ^= b;
        b ^= a;
        a ^= b;
    }
    
}
public class DriverSwap {

    public static void main(String[] args) {

        Pair p = new Pair(-19, 23);
        System.out.println(p);
        p.swap();
        System.out.println(p);

    }

}
