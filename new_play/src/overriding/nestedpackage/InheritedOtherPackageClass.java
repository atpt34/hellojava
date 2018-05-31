package overriding.nestedpackage;

import overriding.FieldAccess;
import overriding.MethodAccess;

public class InheritedOtherPackageClass extends MethodAccess {

    MethodAccess m = new MethodAccess();
    FieldAccess f = new FieldAccess();
    
//    public InheritedOtherPackageClass() {
//        this(10);
//    }
//    
//    public InheritedOtherPackageClass(String a) {
//        this();
//    }
//    
//    public InheritedOtherPackageClass(int a) {
//        this(Integer.toBinaryString(a));
//    }
    
    public void getAccess() {
//        m.privateMethod(); // not visible
//        m.defaultMethod(); // not visible
//        m.protectedMethod(); // not visible
        m.publicMethod();
        
//      int a = f.privateField;
//      int b = f.defaultField;
//      int cc = f.protectedField;
      int d = f.publicField;
      
    }
}
