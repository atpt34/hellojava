package testing;

import static org.junit.Assert.*;

import org.junit.Test;



class Member implements Cloneable {
    double d;
    public Member(double d2)  {
        d = d2;
    }
    @Override
    protected Member clone() throws CloneNotSupportedException {
        Member m = (Member) super.clone();
//        m.d = this.d; // does it in above !
        return m;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(d);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Member other = (Member) obj;
        if (Double.doubleToLongBits(d) != Double.doubleToLongBits(other.d))
            return false;
        return true;
    }
}

class SomeClass implements Cloneable {
    int a;
    Member m;
    public SomeClass(int a, double d) {
        this.a = a;
        this.m = new Member(d);
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        SomeClass copy = (SomeClass) super.clone();
        // shallow copy of Member m !
        copy.m = new Member(m.d); // deep copy of member
        return copy;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + a;
        result = prime * result + ((m == null) ? 0 : m.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SomeClass other = (SomeClass) obj;
        if (a != other.a)
            return false;
        if (m == null) {
            if (other.m != null)
                return false;
        } else if (!m.equals(other.m))
            return false;
        return true;
    }

}

class SomeClass2 extends SomeClass implements Cloneable {
    float f;
    public SomeClass2(int a, double d, float f) {
        super(a, d);
        this.f = f;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Float.floatToIntBits(f);
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof SomeClass2))
            return false;
        SomeClass2 other = (SomeClass2) obj;
        if (Float.floatToIntBits(f) != Float.floatToIntBits(other.f))
            return false;
        return true;
    }
}
class SomeClass3 extends SomeClass implements Cloneable {
    int f;
    public SomeClass3(int a, double d, int f) {
        super(a, d);
        this.f = f;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + f;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof SomeClass3))
            return false;
        SomeClass3 other = (SomeClass3) obj;
        if (f != other.f)
            return false;
        return true;
    }
}


class OtherClass implements Cloneable {
    int a;
    Member m;
    public OtherClass(int a, double d) {
        this.a = a;
        this.m = new Member(d);
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + a;
        result = prime * result + ((m == null) ? 0 : m.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof OtherClass))
            return false;
        OtherClass other = (OtherClass) obj;
        if (a != other.a)
            return false;
        if (m == null) {
            if (other.m != null)
                return false;
        } else if (!m.equals(other.m))
            return false;
        return true;
    }
    
}
class OtherClass2 extends OtherClass implements Cloneable {
    float f;
    public OtherClass2(int a, double d, float f) {
        super(a, d);
        this.f = f;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Float.floatToIntBits(f);
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        OtherClass2 other = (OtherClass2) obj;
        if (Float.floatToIntBits(f) != Float.floatToIntBits(other.f))
            return false;
        return true;
    }
}
class OtherClass3 extends OtherClass  implements Cloneable {
    long f;
    public OtherClass3(int a, double d, long f) {
        super(a, d);
        this.f = f;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (int) (f ^ (f >>> 32));
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        OtherClass3 other = (OtherClass3) obj;
        if (f != other.f)
            return false;
        return true;
    }
}


public class EqualsCloneTest {

    @Test
    public void testSomeClassClone() {
        double d = +17.123;
        int a = -123423;
        SomeClass sc = new SomeClass(a, d);
        SomeClass sc2 = null;
        try {
            sc2 = (SomeClass) sc.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        assertTrue(sc.equals(sc2));
        sc.m.d = 192834.234e+293;
        assertFalse(sc.equals(sc2));
    }
    
    @Test
    public void testMemberClone() {
        double d = 17.123;
        Member m = new Member(d);
        Member m2 = null;
        try {
            m2 = (Member) m.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        assertTrue(m.equals(m2));
        m.d = 192834.234e+293;
        assertFalse(m.equals(m2));
    }

        
    @Test
    public void testOtherClass23Equals() {
        OtherClass oc;
        int a = 19;
        double d = 1923.123;
        oc = new OtherClass2(a, d, -29);
        OtherClass oc2 = new OtherClass3(a, d, -29L);
//        assertTrue(oc.equals(oc2)); // if equals not overriden in OtherClass2 !!!
        assertFalse(oc.equals(oc2));
    }
    
    @Test
    public void testSomeClass23Equals() {
        SomeClass sc;
        int a = 19;
        double d = 1923.123;
        sc = new SomeClass2(a, d, 29);
        SomeClass sc2 = new SomeClass3(a, d, 29);
//        assertFalse(sc instanceof SomeClass3);
        assertFalse(sc.equals(sc2));
    }
    
    @Test
    public void testSomeClassEquals() {
        SomeClass sc;
        int a = 19;
        double d = 1923.123;
        sc = new SomeClass(a, d);
        SomeClass sc2 = new SomeClass(a, d);
        assertTrue(sc.equals(sc2));
    }
    
    @Test
    public void testMemberEquals() {
        double d = 17.123;
        Member m = new Member(d);
        double d2 = d;
        Member m2 = new Member(d2);
        assertTrue(m.equals(m2));
    }
    
    @Test
    public void testMemberNotEquals() {
        double d = 17.123;
        Member m = new Member(d);
        double d2 = 17.1230001;
        Member m2 = new Member(d2);
        assertTrue(!m.equals(m2));
    }

    @Test
    public void test() {
//        fail("Not yet implemented");
    }

}
