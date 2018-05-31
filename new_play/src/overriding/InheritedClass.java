package overriding;



public class InheritedClass extends MethodAccess {

    MethodAccess m = new MethodAccess();
    FieldAccess f = new FieldAccess();
    
    public void getAccess() {
//        m.privateMethod(); // not visible
        m.defaultMethod(); 
        m.protectedMethod(); 
        m.publicMethod();
        
        
//      int a = f.privateField;
      int b = f.defaultField;
      int cc = f.protectedField;
      int d = f.publicField;
      
        Child c = new Child();
        c.usePrivateMethod();
        c.useDefaultMethod();
    }
}
