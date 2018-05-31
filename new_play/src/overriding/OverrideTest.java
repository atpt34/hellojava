package overriding;

class Parent {
    private void privateMethod() {  System.out.println("parent privateMethod");    } // non-virtual or non-overridable method!
    void defaultMethod() {  System.out.println("parent defaultMethod");    }
    protected void protectedMethod() {  System.out.println("parent protectedMethod");    }
    public void publicMethod() {  System.out.println("parent publicMethod");    }
    
    public void usePrivateMethod() { privateMethod(); }
    public void useDefaultMethod() { defaultMethod(); }
    public void useProtectedMethod() { protectedMethod(); }
    public void usePublicMethod() { publicMethod(); }
}

//public class Child extends Parent { // public type must be defined in its own file
class Child extends Parent {
    
    private void privateMethod() {  System.out.println("child privateMethod");    }
    @Override void defaultMethod() {  System.out.println("child defaultMethod");    }
    @Override protected void protectedMethod() {  System.out.println("child protectedMethod");    }
    @Override public void publicMethod() {  System.out.println("child publicMethod");    }
    
    /*
    @Override public void usePrivateMethod() { privateMethod(); }
    @Override public void useDefaultMethod() { defaultMethod(); }
    @Override public void useProtectedMethod() { protectedMethod(); }
    @Override public void usePublicMethod() { publicMethod(); }
    */
}

public class OverrideTest {

    public static void main(String[] args) {

        Parent p = new Parent();
        System.out.println("Parent p = new Parent();");
        p.usePrivateMethod();
        p.useDefaultMethod();
        p.useProtectedMethod();
        p.usePublicMethod();
        
        System.out.println("Child c = new Child();");
        Child c = new Child();
        c.usePrivateMethod();
        c.useDefaultMethod();
        c.useProtectedMethod();
        c.usePublicMethod();
        
        System.out.println("Parent p = new Child();");
        Parent pc = new Child();
        pc.usePrivateMethod();
        pc.useDefaultMethod();
        pc.useProtectedMethod();
        pc.usePublicMethod();

    }

}
