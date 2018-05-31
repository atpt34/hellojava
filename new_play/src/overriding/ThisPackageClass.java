package overriding;



public class ThisPackageClass {

    
    MethodAccess m = new MethodAccess();
    FieldAccess f = new FieldAccess();
    
    public void getAccess() {
        //m.privateMethod(); // not visible
        m.defaultMethod(); 
        m.protectedMethod();
        m.publicMethod();
        
//        int a = f.privateField;
        int b = f.defaultField;
        int c = f.protectedField;
        int d = f.publicField;
        
    }
}
