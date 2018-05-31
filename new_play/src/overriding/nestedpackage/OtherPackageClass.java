package overriding.nestedpackage;

import overriding.FieldAccess;
import overriding.MethodAccess;;

public class OtherPackageClass {

    
    MethodAccess m = new MethodAccess();
    FieldAccess f = new FieldAccess();
    
    public void getAccess() {
        //m.privateMethod(); // not visible
        //m.defaultMethod(); // not visible
        //m.protectedMethod(); // not visible
        m.publicMethod();
        
//      int a = f.privateField;
//      int b = f.defaultField;
//      int cc = f.protectedField;
      int d = f.publicField;
    }
}
