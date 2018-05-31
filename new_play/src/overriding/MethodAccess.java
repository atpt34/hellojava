package overriding;

public class MethodAccess {

    private void privateMethod() 
    {  System.out.println("privateMethod");    } // non-virtual or non-overridable method!
    
    void defaultMethod() 
    {  System.out.println("defaultMethod");    }
    
    protected void protectedMethod() 
    {  System.out.println("protectedMethod");    }
    
    public void publicMethod()
    {  System.out.println("publicMethod");    }
    
}
