package arrays.newplay;

// AtomicInteger EnumSet HashMap
class Tp {
    Tp() {
        System.out.println("Tp instance constructed!");
    }
}

public class ArrayDriver {

    public static void main(String[] args) {

        final int N = 10;
        Object[] arr = new Tp[N]; // object's not constructed, array filled by default with nulls !
        arr[5] = new Tp();
        for (Object obj : arr) {
            System.out.println(obj);
        }
        System.out.println(arr);

        Object o = new ArrayDriver[N][][][][]; // All arrays have object's interface
        System.out.println(o);
    }

}
