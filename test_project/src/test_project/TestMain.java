package test_project;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class Simple implements Comparable<Simple> 
{
    private int a;
    private String name;
    public Simple(int a, String name) {
        super();
        this.a = a;
        this.name = name;
    }
    
    @Override
    public int hashCode() {
        System.out.println("hashCode() for " + System.identityHashCode(this));
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + a;
//        result = prime * result + ((name == null) ? 0 : name.hashCode());
//        return result;
//        if (a == 100) {
//            return 3;
//        }
        return 1;
    }
    @Override
    public boolean equals(Object obj) {
        System.out.println("equals() for " + System.identityHashCode(this));
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Simple other = (Simple) obj;
        if (a != other.a)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public int compareTo(Simple s) {
        System.out.println("compareTo() for " + System.identityHashCode(this));
        return a - s.a;
    }
    
    
}

public class TestMain {

	public static void main(String[] args) {
		System.out.println("Hello");
		
		Simple[] s = new Simple[] { new Simple(1, "one"),
		                            new Simple(2, "two"),
		                            new Simple(3, "three"),
		                            new Simple(4, "four") };
		
		HashSet<Simple> h = new HashSet<>(Arrays.asList(s));
		System.out.println("after ctor:");
		//System.out.println(h);
		h.add(new Simple(5, "five"));
//		System.out.println(h.contains(new Simple(3, "three")));
//		System.out.println(h.contains(new Simple(2, "three")));
		System.out.println("containse one:");
		System.out.println(h.contains(new Simple(1, "one")));
		System.out.println("containse five:");
		System.out.println(h.contains(new Simple(5, "five")));
		h.add(new Simple(6, "six"));
		h.add(new Simple(7, "seven"));
		h.add(new Simple(8, "eight"));
		h.add(new Simple(9, "nine"));
		h.add(new Simple(10, "ten"));
		
		System.out.println("containse one:");
        System.out.println(h.contains(new Simple(1, "one")));
        System.out.println("containse ten:");
		System.out.println(h.contains(new Simple(10, "ten")));
		
		System.out.println("add 100");
		h.add(new Simple(100, "hundred"));
		System.out.println("add 11");
		h.add(new Simple(11, "eleven"));
		
		System.out.println("containse ten:");
        System.out.println(h.contains(new Simple(10, "ten")));
        
	}
}
